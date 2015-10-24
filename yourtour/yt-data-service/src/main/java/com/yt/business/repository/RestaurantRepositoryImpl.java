package com.yt.business.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.neo4j.repository.RestaurantResourceBeanRepository;

@Component
public class RestaurantRepositoryImpl extends CrudAllInOneOperateImpl implements
		RestaurantRepository {
	private static final Log LOG = LogFactory
			.getLog(RestaurantRepositoryImpl.class);

	@Autowired
	private RestaurantResourceBeanRepository restaurantRepo;

}
