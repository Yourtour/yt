package com.yt.mocker;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MockDataFactory {
	private static Properties props; 
	static{
		try{
			InputStream is = MockDataFactory.class.getClassLoader().getResourceAsStream("mock.json");
			
			props.load(is);
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
	/**
	 * 根据URL 中文件从读取模拟数据，并将模拟数据转换成对象
	 * @param clazz
	 * @param url
	 * @return
	 */
	public static  Object getMockData(Class<?> clazz, String url) throws Exception{
		String json = props.getProperty(url);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, clazz);
	}
	
	/**
	 * 根据URL 中文件从读取模拟数据，并将模拟数据转换成集合对象
	 * @param clazz
	 * @param url
	 * @return
	 */
	public static  List  getMockListData(Class<?> clazz, String url) throws Exception{
		String json = props.getProperty(url);
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz); 
		
		return (List) mapper.readValue(json, javaType);
	}
}
