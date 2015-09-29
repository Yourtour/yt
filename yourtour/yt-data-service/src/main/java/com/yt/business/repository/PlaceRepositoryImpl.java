package com.yt.business.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.PlaceBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.business.neo4j.repository.PlaceBeanRepository;
import com.yt.business.utils.Neo4jUtils;

@Component
public class PlaceRepositoryImpl extends CrudAllInOneOperateImpl implements
		PlaceRepository {

	@Autowired
	private PlaceBeanRepository repository;

	public PlaceRepositoryImpl() {
		super();
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
	 * @see com.yt.business.repository.PlaceRepository#save(java.lang.Long,
	 * com.yt.business.bean.PlaceBean, java.lang.String)
	 */
	@Override
	public void save(Long parentId, PlaceBean place, String operator)
			throws Exception {
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

		PlaceBean parent = null;
		if (parentId != null && parentId >= 0) {
			parent = super.template.findOne(parentId, PlaceBean.class);
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
		if (parent != null && orient == null) {
			// 只有parent不为空且待保存节点不存在的情况，才需要建立关系。
			// 建立PARENT关系
			Neo4jUtils.maintainRelation(super.template,
					NodeRelationshipEnum.PARENT, place, parent, null, true,
					false);
			// 建立CHILDREN关系
			Neo4jUtils.maintainRelation(super.template,
					NodeRelationshipEnum.CHILDREN, parent, place, null, true,
					false);
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
}
