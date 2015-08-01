package com.yt.mocker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.yt.vo.maintain.RouteVO;

public class MockDataFactory {
	private static Element root;
	
	static{
		try{
			SAXReader reader = new SAXReader();               
	        Document   document = reader.read(MockDataFactory.class.getClassLoader().getResourceAsStream("mock.xml")); 
	        
	        root = document.getRootElement();
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
		Node node = root.selectSingleNode("mock[@url='" + url + "']");
		ObjectMapper mapper = new ObjectMapper();
		
		String json = node.getText().trim();
		return mapper.readValue(json, clazz);
	}
	
	/**
	 * 根据URL 中文件从读取模拟数据，并将模拟数据转换成集合对象
	 * @param clazz
	 * @param url
	 * @return
	 */
	public static  List  getMockListData(Class<?> clazz, String url) throws Exception{
		Node node = root.selectSingleNode("mock[@url='" + url + "']");
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, List.class, clazz); 
		
		String json = node.getText().trim();
		return (List) mapper.readValue(json, javaType);
	}
	
	/**
	 * 根据URL 中文件从读取模拟数据，并将模拟数据转换成集合对象
	 * @param clazz
	 * @param url
	 * @return
	 */
	public static  Set  getMockSetData(Class<?> clazz, String url) throws Exception{
		Node node = root.selectSingleNode("mock[@url='" + url + "']");
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(HashSet.class, Set.class, clazz); 
		
		String json = node.getText().trim();
		return (Set) mapper.readValue(json, javaType);
	}
	
	public static void main(String[] args) throws Exception{
		getMockListData(RouteVO.class, "route/user/query");
	}
}
