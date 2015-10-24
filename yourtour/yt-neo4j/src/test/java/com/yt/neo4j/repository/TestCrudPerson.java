package com.yt.neo4j.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.business.bean.Account;
import com.yt.business.bean.Person;
import com.yt.business.bean.Person.GenderEnum;

public class TestCrudPerson {
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[] { "application-neo4j-context.xml" });
		assertNotNull(context);
	}

	@After
	public void tearDown() throws Exception {
		context.close();
		context = null;
	}

	@Test
	public void testBeanDefine() {
		CrudOperate crud = context.getBean(CrudOperate.class);
		assertNotNull(crud);
	}

	@Test
	public void testBaseCrud() {
		CrudOperate crud = context.getBean(CrudOperate.class);
		assertNotNull(crud);

		try {
			crud.delete(Person.class);
			assertEquals(crud.count(Person.class), 0);

			Person person = new Person();
			person.setAddress("address 1");
			person.setBirthday(10001);
			person.setCode("code 1");
			person.setFirstName("xi");
			person.setSecondName("ming");
			person.setLastName("peng");
			person.setGender(GenderEnum.MALE);
			crud.save(person, "john");
			assertEquals(crud.count(Person.class), 1);
			assertNotNull(person.getGraphId());
			Person p1 = (Person) crud.get(Person.class, person.getGraphId());
			assertNotNull(p1);
			p1 = (Person) crud.get(Person.class, "code", "ppp");
			assertNull(p1);
			p1 = (Person) crud.get(Person.class, "code", person.getCode());
			assertNotNull(p1);
			assertEquals(person.getAddress(), p1.getAddress());
			assertEquals(person.getBirthday(), p1.getBirthday());
			assertEquals(person.getCode(), p1.getCode());
			assertEquals(person.getCreatedTime(), p1.getCreatedTime());
			assertEquals(person.getCreatedUserId(), p1.getCreatedUserId());
			assertEquals(person.getFirstName(), p1.getFirstName());
			assertEquals(person.getGender(), p1.getGender());
			assertEquals(person.getGraphId(), p1.getGraphId());
			assertEquals(person.getLastName(), p1.getLastName());
			assertEquals(person.getName(), p1.getName());
			assertEquals(person.getSecondName(), p1.getSecondName());
			assertEquals(person.getUpdatedTime(), p1.getUpdatedTime());
			assertEquals(person.getUpdatedUserId(), p1.getUpdatedUserId());
			assertEquals(p1.getAccounts().size(), 0);
			assertEquals(person.getAccounts().size(), p1.getAccounts().size());

			crud.delete(Person.class);
			assertEquals(crud.count(Person.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationAccount() {
		CrudOperate crud = context.getBean(CrudOperate.class);
		assertNotNull(crud);

		try {
			crud.delete(Person.class);
			assertEquals(crud.count(Person.class), 0);
			crud.delete(Account.class);
			assertEquals(crud.count(Account.class), 0);

			Person p1 = new Person();
			p1.setCode("p1");
			p1.setName("person 1");
			crud.save(p1, "john");
			Person p2 = new Person();
			p2.setCode("p2");
			p2.setName("person 1");
			crud.save(p2, "john");
			assertEquals(crud.count(Person.class), 2);
			Person p1_1 = (Person) crud.get(Person.class, p1.getGraphId());
			assertNotNull(p1_1);
			Person p1_2 = (Person) crud.get(Person.class, "code", p1.getCode());
			assertNotNull(p1_2);

			Account a1 = new Account();
			a1.setCode("a1");
			a1.setName("account 1");
			crud.save(a1, "john");
			Account a2 = new Account();
			a2.setCode("a2");
			a2.setName("account 2");
			crud.save(a2, "john");
			Account a3 = new Account();
			a3.setCode("a3");
			a3.setName("account 3");
			crud.save(a3, "john");
			assertEquals(crud.count(Account.class), 3);
			Account a1_1 = (Account) crud.get(Account.class, "code",
					a1.getCode());
			assertNotNull(a1_1);

			Person pp = (Person) crud.get(Person.class, "code", "p1");
			assertNotNull(pp);
			pp.getAccounts().add(a1);
			pp.getAccounts().add(a2);
			pp.getAccounts().add(a3);
			crud.saveRelationsOnly(pp);
			Person pp1 = (Person) crud.get(Person.class, pp.getGraphId());
			assertNotNull(pp1);
			assertEquals(pp.getAccounts().size(), pp1.getAccounts().size());
			assertEquals(pp1.getAccounts().size(), 3);
			Account aa1 = (Account) crud.get(Account.class, pp1.getAccounts()
					.get(0).getGraphId());
			assertNotNull(aa1);
			assertNotNull(aa1.getPerson());
			assertEquals(aa1.getPerson().getCode(), pp.getCode());
			Account aa2 = (Account) crud.get(Account.class, pp1.getAccounts()
					.get(1).getGraphId());
			assertNotNull(aa2);
			assertNotNull(aa2.getPerson());
			assertEquals(aa2.getPerson().getCode(), pp.getCode());
			Account aa3 = (Account) crud.get(Account.class, pp1.getAccounts()
					.get(2).getGraphId());
			assertNotNull(aa3);
			assertNotNull(aa3.getPerson());
			assertEquals(aa3.getPerson().getCode(), pp.getCode());

			Account aa = pp1.getAccounts().get(1);
			pp1.getAccounts().remove(aa);
			crud.saveRelationsOnly(pp1);
			Person pp2 = (Person) crud.get(Person.class, pp1.getGraphId());
			assertNotNull(pp2);
			assertEquals(pp2.getAccounts().size(), 2);
			Account aaa1 = (Account) crud.get(Account.class, pp2.getAccounts()
					.get(0).getGraphId());
			assertNotNull(aaa1);
			assertNotNull(aaa1.getPerson());
			assertEquals(aaa1.getPerson().getCode(), pp.getCode());
			Account aaa2 = (Account) crud.get(Account.class, pp2.getAccounts()
					.get(1).getGraphId());
			assertNotNull(aaa2);
			assertNotNull(aaa2.getPerson());
			assertEquals(aaa2.getPerson().getCode(), pp.getCode());
			Account aaa3 = (Account) crud.get(Account.class, aa.getGraphId());
			assertNotNull(aaa3);
			assertNull(aaa3.getPerson());
			
			Person pp3 = (Person)crud.get(Person.class, "code", "p2");
			assertNotNull(pp3);
			assertEquals(pp3.getAccounts().size(), 0);
			pp3.getAccounts().add(aa);
			crud.saveRelationsOnly(pp3);
			Person pp4 = (Person)crud.get(Person.class, pp3.getGraphId());
			assertNotNull(pp4);
			assertEquals(pp4.getAccounts().size(), 1);
			assertEquals(pp4.getAccounts().get(0).getCode(), aa.getCode());
			Account aaa4 = (Account)crud.get(Account.class, aa.getGraphId());
			assertNotNull(aaa4);
			assertNotNull(aaa4.getPerson());
			assertEquals(aaa4.getPerson().getCode(), pp3.getCode());

			crud.delete(Person.class);
			assertEquals(crud.count(Person.class), 0);
			crud.delete(Account.class);
			assertEquals(crud.count(Account.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
