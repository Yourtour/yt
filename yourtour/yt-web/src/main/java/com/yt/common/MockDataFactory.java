package com.yt.common;

import java.io.InputStream;
import java.util.List;

import com.yt.dal.hbase.BaseBean;

public class MockDataFactory {
	/**
	 * 根据URL 中文件从读取模拟数据，并将模拟数据转换成对象
	 * @param clazz
	 * @param url
	 * @return
	 */
	public static  BaseBean getMockData(Class<?> clazz, String url){
		InputStream is = MockDataFactory.class.getClassLoader().getResourceAsStream(url + ".json");
		
		
		return null;
	}
	
	/**
	 * 根据URL 中文件从读取模拟数据，并将模拟数据转换成集合对象
	 * @param clazz
	 * @param url
	 * @return
	 */
	public static  List<? extends BaseBean> getMockListData(Class<?> clazz, String url){
		return null;
	}
}
