package com.yt.business.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.neo4j.repository.RouteBeanRepository;

@Component
public class RouteRepositoryImpl extends CrudAllInOneOperateImpl implements
		RouteRepository {
	private static final Log LOG = LogFactory.getLog(RouteRepositoryImpl.class);

	@Autowired
	private RouteBeanRepository repository;

	public RouteRepositoryImpl() {
		super();
	}

}
