package com.yt.business.repository;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.HotelResourceBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.common.Constants.NodeRelationshipEnum;
import com.yt.business.neo4j.repository.HotelResourceBeanRepository;
import com.yt.business.neo4j.repository.HotelResourcePlaceTuple;
import com.yt.business.utils.Neo4jUtils;
import com.yt.rsal.neo4j.bean.INeo4JBaseBean;
import com.yt.rsal.neo4j.repository.CrudGeneralOperate;

@Component
public class HotelRepositoryImpl extends CrudGeneralOperate implements
		HotelRepository {
	private static final Log LOG = LogFactory.getLog(HotelRepositoryImpl.class);

	@Autowired
	private HotelResourceBeanRepository hotelRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.repository.HotelRepository#getHotelByGraphId(java.lang
	 * .Long)
	 */
	@Override
	public HotelResourceBean getHotelByGraphId(Long graphId) throws Exception {
		HotelResourcePlaceTuple tuple = hotelRepo.getHotelByGraphId(graphId);
		HotelResourceBean hotel = null;
		if (tuple != null) {
			hotel = tuple.getHotel();
			hotel.setPlace(tuple.getPlace());
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Get hotel resource by graph ID success, the resource %s.",
					hotel != null ? "Found" : "Not Existed"));
		}
		return hotel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.repository.HotelRepository#getHotelsByPage(int, int)
	 */
	@Override
	public List<HotelResourceBean> getHotelsByPage(int start, int limit)
			throws Exception {
		List<HotelResourcePlaceTuple> tuples = hotelRepo.getHotelsByPage(start,
				limit);
		List<HotelResourceBean> list = new Vector<HotelResourceBean>(
				tuples.size());
		for (HotelResourcePlaceTuple tuple : tuples) {
			HotelResourceBean hotel = tuple.getHotel();
			hotel.setPlace(tuple.getPlace());
			list.add(hotel);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Get hotel resource success, request: start(%d), limit(%d); total: %d.",
							start, limit, list.size()));
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.rsal.neo4j.repository.CrudGeneralOperate#save(com.yt.rsal.neo4j
	 * .bean.INeo4JBaseBean, java.lang.String, boolean)
	 */
	@Override
	public INeo4JBaseBean save(INeo4JBaseBean neo4jBean, String operator,
			boolean saveFail2Hbase) throws Exception {
		HotelResourceBean bean = (HotelResourceBean) super.save(neo4jBean,
				operator, saveFail2Hbase);
		HotelResourceBean hotel = (HotelResourceBean) neo4jBean;
		// 建立宾馆和目的地的关联关系
		if (hotel.getPlace() != null && hotel.getPlace().getGraphId() != null) {
			// 建立宾馆到目的地的关系
			PlaceBean place = super.template.findOne(hotel.getPlace()
					.getGraphId(), PlaceBean.class);
			Neo4jUtils.maintainRelation(super.template,
					NodeRelationshipEnum.AT, bean, place, null, true, false);
		}
		return bean;
	}
}
