package com.yt.test.neo4j.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.rsal.neo4j.bean.Neo4JBaseBean;

@NodeEntity
public class TestIndexBean extends Neo4JBaseBean {
	private static final long serialVersionUID = -5150934363215963648L;
	private static final String INDEX_NAME = "test";

	@Indexed
	private String code;
	@Indexed
	private String name;
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String description;
	@Indexed(numeric = true)
	private int age;
	private boolean married = false;

	public TestIndexBean() {
		super();
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMarried() {
		return this.married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

}
