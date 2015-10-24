package com.yt.business.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.neo4j.repository.HotelResourceBeanRepository;

@Component
public class HotelRepositoryImpl extends CrudAllInOneOperateImpl implements
		HotelRepository {
	private static final Log LOG = LogFactory.getLog(HotelRepositoryImpl.class);

	@Autowired
	private HotelResourceBeanRepository hotelRepo;

}
