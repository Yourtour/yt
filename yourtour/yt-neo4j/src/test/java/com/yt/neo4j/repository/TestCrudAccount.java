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
import com.yt.business.bean.Role;

public class TestCrudAccount {
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
			crud.delete(Account.class);
			assertEquals(crud.count(Account.class), 0);

			Account account = new Account();
			account.setCode("code 1");
			account.setMemo("memo 1");
			account.setName("name 1");
			crud.save(account, "john");
			assertEquals(crud.count(Account.class), 1);
			assertNotNull(account.getGraphId());
			Account a1 = (Account) crud
					.get(Account.class, account.getGraphId());
			assertNotNull(a1);
			assertEquals(account.getCode(), a1.getCode());
			assertEquals(account.getMemo(), a1.getMemo());
			assertEquals(account.getCreatedTime(), a1.getCreatedTime());
			assertEquals(account.getCreatedUserId(), a1.getCreatedUserId());
			assertEquals(account.getGraphId(), a1.getGraphId());
			assertEquals(account.getName(), a1.getName());
			assertEquals(account.getPerson(), a1.getPerson());
			assertEquals(account.getUpdatedTime(), a1.getUpdatedTime());
			assertEquals(account.getUpdatedUserId(), a1.getUpdatedUserId());
			assertNull(a1.getPerson());

			crud.delete(Account.class);
			assertEquals(crud.count(Account.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationPerson() {
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

			Account aa1 = (Account) crud.get(Account.class, a1.getGraphId());
			assertNotNull(aa1);
			aa1.setPerson(p1);
			crud.saveRelationsOnly(aa1);
			Account aa1_1 = (Account) crud.get(Account.class, "code",
					aa1.getCode());
			assertNotNull(aa1_1);
			assertNotNull(aa1_1.getPerson());
			assertEquals(aa1_1.getPerson().getCode(), p1.getCode());
			Person pp1 = (Person) crud.get(Person.class, p1.getGraphId());
			assertNotNull(pp1);
			assertEquals(pp1.getAccounts().size(), 1);
			assertEquals(pp1.getAccounts().get(0).getCode(), aa1.getCode());

			Account aa2 = (Account) crud.get(Account.class, a2.getGraphId());
			assertNotNull(aa2);
			aa2.setPerson(p1);
			crud.saveRelationsOnly(aa2);
			Account aa2_1 = (Account) crud.get(Account.class, aa2.getGraphId());
			assertNotNull(aa2_1);
			assertNotNull(aa2_1.getPerson());
			assertEquals(aa2_1.getPerson().getCode(), p1.getCode());
			Person pp2 = (Person) crud.get(Person.class, p1.getGraphId());
			assertNotNull(pp2);
			assertEquals(pp2.getAccounts().size(), 2);

			crud.delete(Person.class);
			assertEquals(crud.count(Person.class), 0);
			crud.delete(Account.class);
			assertEquals(crud.count(Account.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRelationRole() {
		CrudOperate crud = context.getBean(CrudOperate.class);
		assertNotNull(crud);

		try {
			crud.delete(Role.class);
			assertEquals(crud.count(Role.class), 0);
			crud.delete(Account.class);
			assertEquals(crud.count(Account.class), 0);

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

			for (int index = 1; index < 5; index++) {
				Role r = new Role();
				r.setCode("r" + index);
				r.setName("role " + index);
				crud.save(r, "john");
			}
			assertEquals(crud.count(Role.class), 4);

			Account aa1 = (Account) crud.get(Account.class, a1.getGraphId());
			assertNotNull(aa1);
			aa1.getRoles().add((Role) crud.get(Role.class, "code", "r1"));
			aa1.getRoles().add((Role) crud.get(Role.class, "code", "r2"));
			crud.saveRelationsOnly(aa1);
			Account aa1_1 = (Account) crud.get(Account.class, aa1.getGraphId());
			assertNotNull(aa1_1);
			assertEquals(aa1_1.getRoles().size(), 2);
			Role r1 = (Role) crud.get(Role.class, "code", "r1");
			assertNotNull(r1);
			assertEquals(r1.getAccounts().size(), 1);
			assertEquals(r1.getAccounts().get(0).getCode(), aa1.getCode());
			Role r2 = (Role) crud.get(Role.class, "code", "r2");
			assertNotNull(r2);
			assertEquals(r2.getAccounts().size(), 1);
			assertEquals(r2.getAccounts().get(0).getCode(), aa1.getCode());

			Account aa2 = (Account) crud.get(Account.class, a1.getGraphId());
			assertNotNull(aa2);
			Role r1_1 = aa2.getRoles().get(0);
			Role r2_1 = aa2.getRoles().get(1);
			aa2.getRoles().remove(1);
			aa2.getRoles().add((Role) crud.get(Role.class, "code", "r3"));
			aa2.getRoles().add((Role) crud.get(Role.class, "code", "r4"));
			crud.saveRelationsOnly(aa2);
			Account aa2_1 = (Account) crud.get(Account.class, a1.getGraphId());
			assertNotNull(aa2_1);
			assertEquals(aa2_1.getRoles().size(), 3);
			Role rr1 = (Role) crud.get(Role.class, r1_1.getGraphId());
			assertNotNull(rr1);
			assertEquals(rr1.getAccounts().size(), 1);
			assertEquals(rr1.getAccounts().get(0).getCode(), aa1.getCode());
			Role rr2 = (Role) crud.get(Role.class, r2_1.getGraphId());
			assertNotNull(rr2);
			assertEquals(rr2.getAccounts().size(), 0);
			Role rr3 = (Role) crud.get(Role.class, "code", "r3");
			assertNotNull(rr3);
			assertEquals(rr3.getAccounts().size(), 1);
			assertEquals(rr3.getAccounts().get(0).getCode(), aa1.getCode());
			Role rr4 = (Role) crud.get(Role.class, "code", "r4");
			assertNotNull(rr4);
			assertEquals(rr4.getAccounts().size(), 1);
			assertEquals(rr4.getAccounts().get(0).getCode(), aa1.getCode());

			crud.delete(Role.class);
			assertEquals(crud.count(Role.class), 0);
			crud.delete(Account.class);
			assertEquals(crud.count(Account.class), 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
