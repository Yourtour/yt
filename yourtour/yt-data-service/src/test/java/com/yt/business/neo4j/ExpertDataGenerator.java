package com.yt.business.neo4j;

import com.yt.business.bean.*;
import com.yt.business.common.Constants;
import com.yt.business.repository.ExpertRepository;
import com.yt.business.repository.PlaceRepository;
import com.yt.business.repository.RouteRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Direction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ExpertDataGenerator extends DataGenerator{
	private ExpertRepository repository;

	@Before
	public void setup(){
		repository = context.getBean(ExpertRepository.class);
	}

	//@Test
	public void testCreateExpertBean() {
		try {


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

	//@Test
	public void testCreateRelationWithRoute() {
		try {
			repository = context.getBean(ExpertRepository.class);

			UserProfileBean user = new UserProfileBean();
			user.setGraphId(280l);

			RouteMainBean route = new RouteMainBean();
			route.setGraphId(336l);

			repository.createRelation(user, route, Constants.RELATION_TYPE_RECOMMEND, Direction.OUTGOING);

		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void createRelationWithPlace(){
		try {
			PlaceBean place = new PlaceBean();
			place.setGraphId(6l);

			long[] ids = new long[]{280l,299l,282l,301l,434l};
			long[] expertIds = new long[]{1064l, 1060l, 1063l, 1061l, 1062l};
			Map<String, Object> props = new HashMap<>();
			props.put("LEVEL", 0);
			for(int index = 0; index < ids.length; index++) {
				UserProfileBean profile = new UserProfileBean();
				profile.setGraphId(ids[index]);

				ExpertBean expert = new ExpertBean();
				expert.setGraphId(expertIds[index]);

				repository.createRelation(profile, expert, Constants.RELATION_TYPE_IS, Direction.OUTGOING, props);
			}

			/*ExpertBean expert = new ExpertBean();
			expert.setGraphId(1064l);

			repository.deleteRelation(profile, expert, "IS");*/

			/*Long[] expertIds = new Long[]{299l, 280l, 434l, 299l, 301l};
			for(Long expertId : expertIds){
				UserProfileBean profile = new UserProfileBean();
				profile.setGraphId(expertId);

				repository.createRelation(place, profile, Constants.RELATION_TYPE_RECOMMEND, Direction.INCOMING);

			}*/
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}


}
