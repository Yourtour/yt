package com.yt.business.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.LineBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.neo4j.repository.ExpertTuple;
import com.yt.core.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.PlaceBean;
import com.yt.business.neo4j.repository.PlaceBeanRepository;
import com.yt.business.neo4j.repository.PlaceTuple;

@Component
public class PlaceRepositoryImpl extends CrudAllInOneOperateImpl implements
		PlaceRepository {

	@Autowired
	private PlaceBeanRepository repository;

	public PlaceRepositoryImpl() {
		super();
	}

	@Override
	public PlaceBean getPlace4Home(Long placeId) throws Exception{
		PlaceBean place = (PlaceBean) this.get(PlaceBean.class, placeId, false);

		List<ExpertBean> experts = this.getExperts(placeId, 0, 20);
		place.setExperts(experts);

		List<RouteMainBean> routes = this.getRoutes(placeId, 0, 20);
		place.setRoutes(routes);

		return place;
	}

	@Override
	public List<ExpertBean> getExperts(Long placeId, int startIndex, int limit) throws Exception {
		List<ExpertTuple> tuples = repository.getExperts(placeId, startIndex, limit);

		List<ExpertBean> experts = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(tuples)){
			for(ExpertTuple tuple : tuples){
				experts.add(tuple.getExpert());
			}
		}

		return experts;
	}

	@Override
	public List<RouteMainBean> getRoutes(Long placeId, int startIndex, int limit) throws Exception {
		return repository.getRoutes(placeId, startIndex, limit);
	}

	/*
         * (non-Javadoc)
         *
         * @see com.yt.business.repository.PlaceRespository#getAllRootPlaces()
         */
	@Override
	public List<PlaceBean> getAllRootPlaces() throws Exception {
		return repository.getAllRootPlaces();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.PlaceRespository#getAllSubPlaces(java.lang
	 * .Long)
	 */
	@Override
	public List<PlaceBean> getAllSubPlaces(Long graphId) throws Exception {
		return repository.getAllSubPlaces(graphId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.PlaceRepository#save(com.yt.business.bean.
	 * PlaceBean, java.lang.String)
	 */
	@Override
	public void save(PlaceBean place, String operator) throws Exception {
		// 由于目的地是一棵树，保存操作可能涉及到其他节点的rowKey的变更，因此在原有方法是进行扩展。
		boolean codeChanged = false;
		PlaceBean orient = null;
		if (place.getGraphId() != null) {
			orient = super.template
					.findOne(place.getGraphId(), PlaceBean.class);
		}
		if (orient != null && place.getCode().equals(orient.getCode())) {
			// 未修改了代码
			// do nothing
		} else {
			// 修改代码
			codeChanged = true;
		}

		PlaceBean parent = place.getParent();
		if (parent != null && parent.getGraphId() != null) {
			parent = (PlaceBean) super.template.findOne(parent.getGraphId(),
					PlaceBean.class);
			if (parent.isLeaf()) {
				parent.setLeaf(false);
				super.save(parent, operator);
			}
		}
		// 没有父节点，则当前节点就是根节点
		place.setRoot(parent == null);

		// 设置rowkey，rowkey就是fullcode
		String rowkey = place.getCode();
		if (parent != null) {
			rowkey = String.format("%s-%s", parent.getRowKey(), rowkey);
		}
		place.setRowKey(rowkey);
		super.save(place, operator);
		if (codeChanged) {
			// 由于代码修改过，因此相关子节点的rowkey也需要同步修改
			iterateUpdateRowkey(place, operator);
		}
	}

	private void iterateUpdateRowkey(PlaceBean place, String operator)
			throws Exception {
		String rowkey = place.getRowKey();
		List<PlaceBean> children = repository.getAllSubPlaces(place
				.getGraphId());
		for (PlaceBean child : children) {
			child.setRowKey(String.format("%s-%s", rowkey, child.getCode()));
			super.save(child, operator);
			iterateUpdateRowkey(child, operator);
		}
	}

	@Override
	public List<PlaceBean> getPlaces(Long parentId) {
		List<PlaceTuple> tuples = this.repository.getPlaces(parentId);

		List<PlaceBean> places = new ArrayList<PlaceBean>();
		PlaceBean parent = null, child = null;
		for (PlaceTuple tuple : tuples) {
			parent = tuple.getParent();
			child = tuple.getChild();

			if (places.contains(parent)) {
				for (PlaceBean p : places) {
					if (p.equals(parent)) {
						p.addSub(child);
					}
				}
			} else {
				parent.addSub(child);
				places.add(parent);
			}
		}

		return places;
	}
	
	@Override
	public List<PlaceBean> getPlaces(String parentCode) {
		List<PlaceBean> places = new ArrayList<PlaceBean>();
		List<PlaceTuple> tuples = null;
		PlaceBean parent = null, child = null;

		String[] parentCodes = parentCode.split(",");
		for(String code : parentCodes){
			PlaceBean root = new PlaceBean();
			root.setCode(code);
			tuples = this.repository.getPlaces(code);

			for (PlaceTuple tuple : tuples) {
				parent = tuple.getParent();
				child = tuple.getChild();

				if (places.contains(parent)) {
					for (PlaceBean p : places) {
						if (p.equals(parent)) {
							p.addSub(child);
						}
					}
				} else {
					parent.setParent(root);

					parent.addSub(child);
					places.add(parent);
				}
			}
		}

		return places;
	}

	@Override
	public List<PlaceBean> getRouteRecommendPlaces(Long userId) {
		return this.repository.getRecommendPlaces(userId);
	}

	@Override
	public List<PlaceBean> getRelatedPlaces(Long placeId) {
		return this.repository.getRelatedPlaces(placeId);
	}
}
