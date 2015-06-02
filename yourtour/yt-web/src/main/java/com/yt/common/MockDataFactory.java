package com.yt.common;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MockDataFactory {
	/**
	 * 根据URL 中文件从读取模拟数据，并将模拟数据转换成对象
	 * @param clazz
	 * @param url
	 * @return
	 */
	public static  Object getMockData(Class<?> clazz, String url) throws Exception{
		InputStream is = MockDataFactory.class.getClassLoader().getResourceAsStream(url + ".json");
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(is, clazz);
	}
	
	/**
	 * 根据URL 中文件从读取模拟数据，并将模拟数据转换成集合对象
	 * @param clazz
	 * @param url
	 * @return
	 */
	public static  List  getMockListData(Class<?> clazz, String url) throws Exception{
		InputStream is = MockDataFactory.class.getClassLoader().getResourceAsStream(url + ".json");
		
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz); 
		return (List) mapper.readValue(is, javaType);
	}
}
