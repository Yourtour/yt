package com.yt.core.common;

import java.util.Date;

/**
 * 跟访问线程绑定的存放当前线程关联的当前用户信息，以及该用户上一次访问的时间。
 * 
 * @author John.Peng
 * 
 */
public class ThreadCurrentUser {

	// 当前用户
	private static final ThreadLocal<UserInfor> currentUser = new ThreadLocal<UserInfor>() {
		@Override
		protected UserInfor initialValue() {
			return new UserInfor();
		}
	};
	// 当前用户上次访问时间
	private static final ThreadLocal<Long> lastAccessTime = new ThreadLocal<Long>() {
		@Override
		protected Long initialValue() {
			return new Date().getTime();
		}
	};

	public ThreadCurrentUser() {
		super();
	}

	public static UserInfor getCurrentUserInfor() {
		return currentUser.get();
	}

	public static long getLastAccessTime() {
		return lastAccessTime.get();
	}

	public static void setUserInfor(UserInfor user) {
		currentUser.set(user);
		lastAccessTime.set(new Date().getTime());
	}

	public static void setUserInfor(long id, String name) {
		UserInfor user = new UserInfor(id, name);
		setUserInfor(user);
	}

}
