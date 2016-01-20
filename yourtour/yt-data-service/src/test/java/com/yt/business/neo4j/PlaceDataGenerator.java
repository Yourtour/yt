package com.yt.business.neo4j;

import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.ResourceActivityItemBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants;
import com.yt.business.repository.PlaceRepository;
import com.yt.business.repository.RouteRepository;
import org.junit.Test;
import org.neo4j.graphdb.Direction;

import static org.junit.Assert.fail;

public class PlaceDataGenerator extends DataGenerator{
	private PlaceRepository repository;

	//@Test
	public void createRecommend() {
		repository = context.getBean(PlaceRepository.class);
		try {
			Long[] placeIds = new Long[]{6l, 7l};

			UserProfileBean user = new UserProfileBean();
			user.setGraphId(29l);
			for(Long placeId : placeIds){
				PlaceBean place = new PlaceBean();
				place.setGraphId(placeId);

				repository.createRelation(user, place, Constants.RELATION_TYPE_FOLLOW, Direction.OUTGOING);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	//@Test
	public void createRootPlace() {
		repository = context.getBean(PlaceRepository.class);
		try {
			String[] codes = new String[]{"GAT","Asia","Europe","Africa","America","Ocean"};
			String[] names = new String[]{"港澳台","亚洲","欧洲","非洲","美洲","大洋洲"};

			for(int index = 0; index < codes.length; index++) {
				PlaceBean place = new PlaceBean();
				place.setCode(codes[index]);
				place.setName(names[index]);
				place.setRoot(true);
				place.setLeaf(false);
				repository.save(place, "admin");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	//@Test
	public void delete() throws Exception{
		repository = context.getBean(PlaceRepository.class);
		try {
			PlaceBean place = (PlaceBean) repository.get(PlaceBean.class, 1l);
			repository.delete(place);

			place = (PlaceBean) repository.get(PlaceBean.class, 3l);
			repository.delete(place);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	//@Test
	public void createTaiWan(){
		repository = context.getBean(PlaceRepository.class);
		try {
			PlaceBean parent = new PlaceBean();
			parent.setGraphId(1194l);

			String[] codes = new String[]{"RiYueTan","TaiBei","KenDing","GaoXiong", "HuaLian"};
			String[] names = new String[]{"日月潭","台北","垦丁","高雄","花莲"};

			for(int index = 0; index < codes.length; index++) {
				PlaceBean place = new PlaceBean();
				place.setCode(codes[index]);
				place.setName(names[index]);
				place.setRoot(false);
				place.setLeaf(true);
				place.setParent(parent);
				repository.save(place, "admin");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	//@Test
	public void createGAT(){
		repository = context.getBean(PlaceRepository.class);
		try {
			PlaceBean parent = new PlaceBean();
			parent.setGraphId(1184l);

			String[] codes = new String[]{"TaiWan","HongKong","Macaw"};
			String[] names = new String[]{"台湾","香港","澳门"};

			for(int index = 0; index < codes.length; index++) {
				PlaceBean place = new PlaceBean();
				place.setCode(codes[index]);
				place.setName(names[index]);
				place.setRoot(false);
				place.setLeaf(false);
				place.setParent(parent);
				repository.save(place, "admin");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}

	@Test
	public void createRelative(){
		repository = context.getBean(PlaceRepository.class);
		try {
			PlaceBean source = new PlaceBean();
			source.setGraphId(6l);

			PlaceBean target = new PlaceBean();
			target.setGraphId(7l);

			repository.createRelation(source, target, Constants.RELATION_TYPE_RELATED, Direction.OUTGOING);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
