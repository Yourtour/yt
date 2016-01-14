package com.yt.business.neo4j;

import com.yt.business.bean.*;
import com.yt.business.common.Constants;
import com.yt.business.repository.ExpertRepository;
import com.yt.business.repository.RouteRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Direction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ExpertDataGenerator extends DataGenerator{
	private ExpertRepository repository;

	@Test
	public void testCreateExpertBean() {
		try {
			repository = context.getBean(ExpertRepository.class);

			Long[] profileIds = new Long[]{278l};
			for(int index = 0; index < profileIds.length; index++){
				ExpertBean expert = new ExpertBean();
				UserProfileBean profile = new UserProfileBean();
				profile.setGraphId(profileIds[index]);

				expert.setProfile(profile);
				repository.save(expert, "tester");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	/*@Test
	public void testCreateRelationWithRoute() {
		try {
			repository = context.getBean(ExpertRepository.class);

			ExpertBean expert = new ExpertBean();
			expert.setGraphId(1064l);

			RouteMainBean route = new RouteMainBean();
			route.setGraphId(417l);

			repository.createRelation(expert, route, Constants.RELATION_TYPE_RECOMMEND, Direction.OUTGOING);

		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}*/
}
