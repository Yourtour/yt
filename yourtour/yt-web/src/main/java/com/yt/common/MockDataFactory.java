package com.yt.common;

import java.io.InputStream;
import java.util.List;

import com.yt.dal.hbase.BaseBean;

public class MockDataFactory {
	public static  BaseBean getMockData(Class<?> clazz, String url){
		InputStream is = MockDataFactory.class.getClassLoader().getResourceAsStream(url + ".json");
		
		
		return null;
	}
	
	public static  List<? extends BaseBean> getMockListData(Class<?> clazz, String url){
		return null;
	}
}
