package com.yt.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yt.business.service.IAlongService;
import com.yt.core.utils.DateUtils;
import com.yt.neo4j.repository.CrudOperate;
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
import org.springframework.stereotype.Service;

@Service
public class AlongServiceImpl extends BaseServiceImpl implements IAlongService {
	@Autowired
	private AlongBeanRepository alongRepo;

	@Autowired
	private CrudOperate<AlongBean> crudOperate;

	public AlongServiceImpl(){}

	@Override
	public void saveAlongInfo(AlongBean along) throws Exception {
		crudOperate.save(along);
	}

	@Override
	public AlongBean getAlongInfo(Long alongId) throws Exception {
		return crudOperate.get(alongId);
	}

	@Override
	public void deleteAlongInfo(Long alongId, Long userId) throws Exception {
		AlongBean along  = crudOperate.get(alongId);

		crudOperate.delete(along);
	}

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
