package com.yt.business.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.PlaceBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.business.neo4j.repository.PlaceBeanRepository;
import com.yt.business.utils.Neo4jUtils;
import com.yt.rsal.neo4j.repository.CrudGeneralOperate;

@Component
public class PlaceRepositoryImpl extends CrudGeneralOperate implements
		PlaceRepository {

	@Autowired
	private PlaceBeanRepository repository;

	public PlaceRepositoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.PlaceRespository#getPlaceByGraphId(java.lang
	 * .Long)
	 */
	@Override
	public PlaceBean getPlaceByGraphId(Long graphId) throws Exception {
		return super.template.findOne(graphId, PlaceBean.class);
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
	 * @see com.yt.business.repository.PlaceRespository#getAllSubPlaces(long)
	 */
	@Override
	public List<PlaceBean> getAllSubPlaces(long graphId) throws Exception {
		return repository.getAllSubPlaces(graphId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.repository.PlaceRepository#save(long,
	 * com.yt.business.bean.PlaceBean, java.lang.String)
	 */
	@Override
	public void save(long parentId, PlaceBean place, String operator)
			throws Exception {
		PlaceBean parent = null;
		if (parentId >= 0) {
			parent = super.template.findOne(parentId, PlaceBean.class);
		}
		// 没有父节点，则当前节点就是根节点
		place.setRoot(parent == null);
		super.save(place, operator, true);
		// 由于save会自动更新place对象，因此没有必要在获取一次。
		// place = (PlaceBean) super.get(PlaceBean.class, "code",
		// place.getCode());
		if (parent != null) {
			// 只有parent存在的情况，才需要建立关系。
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
}
