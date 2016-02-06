package com.yt.business.neo4j;

import com.yt.business.bean.*;
import com.yt.business.common.Constants;
import com.yt.business.repository.ExpertRepository;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Direction;

import java.util.HashMap;
import java.util.Map;

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

	//@Test
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
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	//@Test
	public void createExpertServices(){
		try {
			Long uid = 282l, eid=1062l;
			Long[] sids = new Long[]{930l, 951l};

			UserProfileBean ubean = new UserProfileBean();
			ubean.setGraphId(uid);

			ExpertBean ebean = new ExpertBean();
			ebean.setGraphId(eid);

			for(Long sid : sids){
				ExpertServiceBean sbean = new ExpertServiceBean();
				sbean.setGraphId(sid);

				this.repository.deleteRelation(ubean, sbean, "HAS");

				this.repository.createRelation(ebean, sbean, "HAS", Direction.OUTGOING);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	//@Test
	public void createExpertRecommendRoutes(){
		try {
			Long uid = 434l;
			Long[] rids = new Long[]{336l, 329l,250l,247l, 417l,1031l, 1069l};

			UserProfileBean ubean = new UserProfileBean();
			ubean.setGraphId(uid);

			for(Long rid : rids){
				RouteMainBean rbean = new RouteMainBean();
				rbean.setGraphId(rid);

				this.repository.createRelation(ubean, rbean, "RECOMMEND", Direction.OUTGOING);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void createExpertParticipateRoutes(){
		try {
			Long uid = 434l;
			Long[] rids = new Long[]{336l, 329l,250l,247l, 417l,1031l, 1069l};

			UserProfileBean ubean = new UserProfileBean();
			ubean.setGraphId(uid);

			for(Long rid : rids){
				RouteMainBean rbean = new RouteMainBean();
				rbean.setGraphId(rid);

				this.repository.createRelation(ubean, rbean, "EXPERT", Direction.OUTGOING);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

}
