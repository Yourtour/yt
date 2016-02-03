package com.yt.business.neo4j;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.ExpertRepository;
import com.yt.business.repository.LineRepository;
import com.yt.business.repository.PlaceRepository;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Direction;

import static org.junit.Assert.fail;

public class LineDataGenerator extends DataGenerator {
	private LineRepository repository;

	@Before
	public void setup(){
		repository = context.getBean(LineRepository.class);
	}

	@Test
	public void createRelationWithPlace(){
		try {
			PlaceBean place = new PlaceBean();
			place.setGraphId(6l);

			Long[] lineIds = new Long[]{14l};
			for(Long lineId : lineIds){
				LineBean line = new LineBean();
				line.setGraphId(lineId);

				repository.createRelation(place, line, Constants.RELATION_TYPE_AT, Direction.INCOMING);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

}