package com.yt.business.repository.neo4j;

import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.LaunchBean;
import com.yt.business.repository.crud.ActivityBeanCrud;
import com.yt.neo4j.repository.CrudOperate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class ActivityGenerator extends DataGenerator{
	CrudOperate<ActivityBean> operator = null;

	@Test
	public void save() throws Exception {
		operator = super.context.getBean(ActivityBeanCrud.class);

		ActivityBean activity = new ActivityBean();
		activity.setImageUrl("resources/images/launch_image.jpg");
		activity.setStatus(ActivityBean.Status.RELEASED);
		operator.save(activity);
	}

}
