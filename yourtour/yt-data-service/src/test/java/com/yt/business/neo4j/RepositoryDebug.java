package com.yt.business.neo4j;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.business.neo4j.repository.UserBeanRepository;

public class RepositoryDebug {
	private ApplicationContext context;
	private UserBeanRepository accountRepository;
	
	public RepositoryDebug() {
		context = new ClassPathXmlApplicationContext(new String[] {
				"application-hbase-context.xml", "application-neo4j-context.xml" });
		accountRepository = context.getBean(UserBeanRepository.class);
	}
	
	@Test
	public void testOperation() {
		/*UserAccountBean bean = accountRepository.getUserAccountInfo("13601951704");
		
		System.out.println(bean.getPwd());*/
	}

}
