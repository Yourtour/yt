package com.yt.core.common;

public class UserInfor {
	private long id;
	private String name;

	public UserInfor() {
		super();
	}

	public UserInfor(long id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
