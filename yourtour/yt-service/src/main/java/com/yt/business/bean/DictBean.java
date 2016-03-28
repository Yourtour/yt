package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.hbase.annotation.HbaseTable;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * 字典数据实体对象，系统中所有字典数据统一存储
 */
@HbaseTable(name = "T_DICT_INFO")
@NodeEntity
public class DictBean extends BaseBeanImpl {
	public static enum Type{
		USER_TAGS("USER_TAGS", "用户标签"), ROUTE_TAGS("ROUTE_TAGS", "行程标签");

		public String code;
		public String name;

		private Type(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}


	private Type type;
	private String name;
	private String code;
	private String value;

	public DictBean() {
		super();
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
