package com.yt.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yt.business.service.IAlongService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.AlongBean;
import com.yt.business.repository.neo4j.AlongBeanRepository;
import com.yt.business.repository.neo4j.AlongTuple;
import com.yt.business.repository.neo4j.CommentBeanRepository;
import com.yt.core.utils.CollectionUtils;

@Component
public class AlongServiceImpl extends CrudAllInOneOperateImpl implements IAlongService<AlongBean> {
	private static final Log LOG = LogFactory.getLog(AlongServiceImpl.class);

	@Autowired
	private AlongBeanRepository alongRepo;
	
	@Autowired
	private CommentBeanRepository commentRepo;

	@Override
	public List<AlongBean> getAlongsByPlace(Long placeId, Long startIndex, int limit)
	throws Exception {
		List<AlongBean> alongBeans = new ArrayList();

		List<AlongTuple> alongTuples = alongRepo.getAlongByPlace(placeId, startIndex, limit);
		if(CollectionUtils.isNotEmpty(alongTuples)){
			for(AlongTuple tuple : alongTuples){
				alongBeans.add(tuple.getAlong());
			}
		}

		return alongBeans;
	}

	@Override
	public List<AlongBean> getAlongsByRoute(Long routeId) throws Exception {
		List<AlongBean> alongBeans = new ArrayList();

		List<AlongTuple> alongTuples = alongRepo.getAlongByRoute(routeId);
		if(CollectionUtils.isNotEmpty(alongTuples)){
			for(AlongTuple tuple : alongTuples){
				alongBeans.add(tuple.getAlong());
			}
		}

		return alongBeans;
	}
}
