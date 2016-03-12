package com.yt.core.common;

public class ThreadCurrentUser {

	private static final ThreadLocal<UserInfor> current = new ThreadLocal<UserInfor>() {
		@Override
		protected UserInfor initialValue() {
			return new UserInfor();
		}
	};

	public ThreadCurrentUser() {
		super();
	}

	public static UserInfor getCurrentUserInfor() {
		return current.get();
	}

	public static void setUserInfor(UserInfor user) {
		current.set(user);
	}

	public static void setUserInfor(long id, String name) {
		UserInfor user = new UserInfor(id, name);
		current.set(user);
	}

}
