package com.yt.business.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.AlongBean;
import com.yt.business.neo4j.repository.AlongBeanRepository;
import com.yt.business.neo4j.repository.AlongTuple;
import com.yt.business.neo4j.repository.CommentBeanRepository;
import com.yt.business.neo4j.repository.CommentTuple;
import com.yt.core.utils.CollectionUtils;

@Component
public class AlongRepositoryImpl extends CrudAllInOneOperateImpl implements
		AlongRepository {
	private static final Log LOG = LogFactory.getLog(AlongRepositoryImpl.class);

	@Autowired
	private AlongBeanRepository alongRepo;
	
	@Autowired
	private CommentBeanRepository commentRepo;

	@Override
	public AlongBean getAlongByGraphId(Long graphId, int startIndex, int size) throws Exception {
		AlongBean alongBean = alongRepo.getAlongByGraphId(graphId);
		if(alongBean == null) return null;
		
		List<CommentTuple> comments = commentRepo.getCommentsByGraphId(alongBean.getGraphId(), 0, 10);
		alongBean.setComments(comments);
		return alongBean;
	}

	@Override
	public List<AlongBean> getAlongsByPlace(Long placeId, int startIndex, int limit)
	throws Exception {
		List<AlongBean> alongBeans = new ArrayList();
		List<AlongTuple> alongTuples = alongRepo.getAlongByPlace(placeId, startIndex, limit);
		if(CollectionUtils.isNotEmpty(alongTuples)){
			for(AlongTuple tuple : alongTuples){
				AlongBean alongBean = tuple.getAlong();
				alongBean.setRoute(tuple.getRoute());
			}
		}

		return alongBeans;
	}

	
	
}
