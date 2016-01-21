package com.yt.business.neo4j;

import com.yt.business.bean.CommentBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.CommentRepository;
import com.yt.business.repository.RouteRepository;
import org.junit.Test;
import org.neo4j.graphdb.Direction;

import static org.junit.Assert.fail;

public class RouteDataGenerator extends DataGenerator{
	private RouteRepository repository;

	@Test
	public void testCreateCommentBean() {
		try {
			repository = context.getBean(RouteRepository.class);

			RouteMainBean route = (RouteMainBean) repository.get(RouteMainBean.class, 247l);
			route.setFeature("参观举世闻名的布达拉宫，膜拜12岁释迦牟尼等身佛像!\n" +
					"★游览林芝小江南优质景区，感受林芝梦里梦外的小江南风景!\n" +
					"★领略纳木错叹为观止的美景，陶醉在蓝天白云之下!\n" +
					"★全景西藏，成熟好评线路，一次出行经典景点全覆盖，雪域圣洁风情尽领略!\n" +
					"★游地球上最后一处秘境—雅鲁藏布大峡谷，看中国最美山峰之首—南迦巴瓦峰，聆听西藏的美。\n" +
					"★如果您已经购买了进出藏火车票和飞机票，不用选择支付火车票附加费用，如需要协助您购买进藏火车票，请选择支付附加费用。我方协助购买火车票不收取任何高价手续费，只是为了确保您顺利进出藏安心旅游。\n" +
					"★购买进藏出藏火车票难度较大，我方可以协助购买，确保您顺利进藏旅游，如需帮忙购买，请提前5天以上告知我方工作人员。购买飞机票相对容易，游客可以自行上网购买。");
			repository.save(route, false,"admin");
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
