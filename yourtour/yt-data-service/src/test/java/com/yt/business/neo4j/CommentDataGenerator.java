package com.yt.business.neo4j;

import com.yt.business.bean.CommentBean;
import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.CommentRepository;
import com.yt.business.repository.ExpertRepository;
import org.junit.Test;
import org.neo4j.graphdb.Direction;

import static org.junit.Assert.fail;

public class CommentDataGenerator extends DataGenerator{
	private CommentRepository repository;

	@Test
	public void testCreateCommentBean() {
		try {
			repository = context.getBean(CommentRepository.class);

			Long[] profileIds = new Long[]{278l};
			UserProfileBean user = new UserProfileBean();
			user.setGraphId(299l);
			SceneResourceBean sceneBean = new SceneResourceBean();
			sceneBean.setGraphId(8l);

			for(int index = 0; index < 10; index++){
				CommentBean comment  = new CommentBean();
				switch(index % 3){
					case 0:
						comment.setScore(2.5d);
						break;
					case 1:
						comment.setScore(3.5d);
						break;
					case 2:
						comment.setScore(4.5d);
						comment.setImageUrl("resources/images/comment.jpg;resources/images/comment.jpg");
						break;
				}

				comment.setUser(user);
				repository.save(comment, "tester");

				repository.createRelation(sceneBean, comment, Constants.RELATION_TYPE_HAS, Direction.OUTGOING);
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
