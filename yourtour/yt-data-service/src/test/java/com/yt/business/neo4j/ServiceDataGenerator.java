package com.yt.business.neo4j;

import com.yt.business.bean.*;
import com.yt.business.common.Constants;
import com.yt.business.repository.ExpertRepository;
import com.yt.business.repository.ServiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Direction;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;

public class ServiceDataGenerator extends DataGenerator{
	private ServiceRepository repository;

	@Before
	public void setup(){
		repository = context.getBean(ServiceRepository.class);
	}

	@Test
	public void testCreateRouteService() {
		try {
			Long[] esids = new Long[]{ 951l};
			RouteMainBean route = new RouteMainBean();
			route.setGraphId(1102l);
			for(Long esid : esids){
				RouteServiceBean service = new RouteServiceBean();

				ExpertServiceBean eservice = new ExpertServiceBean();
				eservice.setGraphId(esid);

				service.setRoute(route);
				service.setService(eservice);

				repository.save(service, "tester");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	//@Test
	public void testCreateExpertService() {
		try {
			Long[] esids = new Long[]{ 951l};
			UserProfileBean user = new UserProfileBean();
			user.setGraphId(299l);
			for(Long esid : esids){
				ExpertServiceBean service = new ExpertServiceBean();
				service.setGraphId(esid);

				repository.createRelation(user, service, "HAS", Direction.OUTGOING);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

}
