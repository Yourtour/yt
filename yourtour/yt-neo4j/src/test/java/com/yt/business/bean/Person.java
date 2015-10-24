package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.bean.Neo4jBaseDictBeanImpl;

@NodeEntity
public class Person extends Neo4jBaseDictBeanImpl {

	private static final long serialVersionUID = 8052402142546911961L;
	
	private static final String INDEX_NAME = "person";

	public enum GenderEnum {
		MALE, FEMALE, UNKOWN
	};

	private String firstName, secondName, lastName;

	private GenderEnum gender = GenderEnum.MALE;

	private long birthday;

	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String address;
	
	@Neo4jRelationship(relationship="ASSIGNED", type=Account.class, direction = Direction.OUTGOING, isSet=true)
	private transient List<Account> accounts;

	public Person() {
		super();
		accounts = new Vector<Account>();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

}
