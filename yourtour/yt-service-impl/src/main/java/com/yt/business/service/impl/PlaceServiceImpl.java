package com.yt.business.service.impl;

import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.InfoBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.pack.PlaceBeanPack;
import com.yt.business.repository.neo4j.ExpertTuple;
import com.yt.business.repository.neo4j.PlaceBeanRepository;
import com.yt.business.repository.neo4j.PlaceTuple;
import com.yt.business.repository.neo4j.RouteTuple;
import com.yt.business.service.IPlaceService;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.neo4j.repository.CrudOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceServiceImpl extends ServiceBase implements IPlaceService {
	@Autowired
	private PlaceBeanRepository repository;

	@Autowired
	private CrudOperate<PlaceBean> placeCrudOperate;

	public PlaceServiceImpl() {
		super();
	}

	@Override
	public List<ExpertBean> getExperts(Long placeId, Long nextCursor, int limit) throws Exception {
		List<ExpertTuple> tuples = repository.getExperts(placeId, nextCursor, limit);

		List<ExpertBean> experts = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(tuples)){
			for(ExpertTuple tuple : tuples){
				experts.add(tuple.getExpert());
			}
		}

		return experts;
	}

	@Override
	public List<RouteMainBean> getRoutes(Long placeId, Long nextCursor, int limit) throws Exception {
		List<RouteMainBean> routes = new ArrayList<>();

		List<RouteTuple> tuples = this.repository.getRoutes(placeId, nextCursor, limit);
		if(tuples != null){
			for(RouteTuple tuple : tuples){
				routes.add(tuple.getRoute());
			}
		}

		return routes;
	}

	@Override
	public void savePlace(PlaceBean place, Long operator) throws Exception {
		// 由于目的地是一棵树，保存操作可能涉及到其他节点的rowKey的变更，因此在原有方法是进行扩展。
		boolean codeChanged = false;
		PlaceBean original = place;
		if (place.getId() != null) {
			original = placeCrudOperate.get(place.getId());
			if (original != null && ! place.getCode().equals(original.getCode())) {
				codeChanged = true;
			}

			BeanUtils.merge(place, original);
		}

		PlaceBean parent = original.getParent();
		if (parent != null && parent.getId() != null) {
			parent = placeCrudOperate.get(parent.getId());
			if (parent.isLeaf()) {
				parent.setLeaf(false);
				placeCrudOperate.save(parent);
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
		placeCrudOperate.save(place);
		if (codeChanged) {
			// 由于代码修改过，因此相关子节点的rowkey也需要同步修改
		}
	}

	/*private void iterateUpdateRowkey(PlaceBean place, Long operator)
			throws Exception {
		String rowkey = place.getRowKey();
		List<PlaceBean> children = repository.getAllSubPlaces(place.getId());
		for (PlaceBean child : children) {
			child.setRowKey(String.format("%s-%s", rowkey, child.getCode()));
			placeCrudOperate.save(child);
			iterateUpdateRowkey(child, operator);
		}
	}
*/
	@Override
	public List<PlaceBean> getPlaces() {
		List<PlaceBean> places = new ArrayList<PlaceBean>();
		List<PlaceTuple> tuples = null;
		PlaceBean parent = null, child = null;

		List<PlaceBean> roots = repository.getRootPlaces();
		for(PlaceBean root : roots){
			tuples = this.repository.getPlaces(root.getCode());
			if(CollectionUtils.isNotEmpty(tuples)) {
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
		}

		return places;
	}

	@Override
	public void deletePlace(Long id, Long userId) throws Exception {
		PlaceBean place = this.getPlace(id);
		if(place == null) return;

		this.placeCrudOperate.delete(place);
	}

	@Override
	public PlaceBean getPlace(Long id) throws Exception {
		return this.placeCrudOperate.get(id, false);
	}

	@Override
	public PlaceBeanPack getPlacePack(Long id, Long lastModifiedTime) throws Exception {
		PlaceBeanPack pack = new PlaceBeanPack();

		PlaceBean place = this.getPlace(id);
		if(place == null) return pack;
		pack.setPlace(place);

		//获取目的地推荐行程
		pack.setRoutes(this.getRoutes(id, 0l, 5));

		//获取目的地推荐的达人
		pack.setExperts(this.getExperts(id, 0l, 5));

		//获取目的地咨询信息
		pack.setInfoes(this.getInfoes(id, 0l, 5));

		return pack;
	}

	@Override
	public List<InfoBean> getInfoes(Long placeId, Long nextCursor, int limit) throws Exception {
		return null;
	}
}
