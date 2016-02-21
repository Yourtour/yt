package com.yt.business.neo4j;

import com.yt.business.bean.ResourceActivityItemBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.repository.ExpertRepository;
import com.yt.business.repository.RouteRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ResourceDataGenerator extends DataGenerator{
	private RouteRepository repository;

	@Before
	public void setup(){
		repository = context.getBean(RouteRepository.class);
	}

	@Test
	public void testCRUDReosurceBean() {
		try {
			SceneResourceBean resource = (SceneResourceBean) repository.get(SceneResourceBean.class, 10l);

			resource.setGraphId(null);
			resource.setName("新安江山水画廊");
			repository.save(resource, "admin");
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	//@Test
	public void testCRUDRouteBean() {
		repository = context.getBean(RouteRepository.class);
		try {
			save(new String[]{"玉屏景区","resources/icons/icon_service.png","黄山玉屏景区以玉屏楼为中心，莲花峰和天都峰为主体，前山就是指这一景区。沿途有“蓬莱三岛”、“百步云梯”、“一线天”、“新一线天”、“鳌鱼洞”等景观。。"});
			save(new String[]{"北海景区","resources/icons/icon_service.png","黄山北海景区是黄山景区的腹地，在光明顶与始信峰、狮子峰、白鹅峰之间，东连云谷景区，南接玉屏景区，北近松谷景区。是一片海拔1600米左右的高山开阔地带，面积1316公顷。北海群峰荟萃，石门峰、贡阳山，都属海拔1800米以上的高峰。海拔1690米的狮子峰在景区之中，峰上的清凉台是观赏云海和日出的最佳之处。"});
			save(new String[]{"云谷景区","resources/icons/icon_service.png","云谷景区位于黄山东部，海拔高度仅890米，是一处谷地。宋代丞相程元凤曾在此处读书，故名丞相源。明代文士傅严漫游至此，应掷钵禅僧之求，手书“云谷”二字，此后禅院改名云谷寺。云谷主要景点有云谷山庄、古树、怪石、“九龙瀑”和“百丈泉”。"});
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	private void save(String[] item) throws Exception{
		ResourceActivityItemBean bean = new ResourceActivityItemBean();
		bean.setTitle(item[0]);
		bean.setImageUrl(item[1]);
		bean.setMemo(item[2]);

		SceneResourceBean resource = new SceneResourceBean();
		resource.setGraphId(8l);
		bean.setResource(resource);

		repository.save(bean, "tester");
	}
}
