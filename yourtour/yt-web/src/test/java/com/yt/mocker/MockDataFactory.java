package com.yt.mocker;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yt.vo.RouteVO;

public class MockDataFactory {
	private static Element root;
	
	static{
		try{
			SAXReader reader = new SAXReader();               
	        Document   document = reader.read(MockDataFactory.class.getClassLoader().getResourceAsStream("mock.xml")); 
	        
	        root = document.getRootElement();
	        
	        System.out.println(root.getNodeTypeName());
	        
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
		JavaType javaType = mapper.getTypeFactory().constructParametrizedType(ArrayList.class, List.class, clazz); 
		
		String json = node.getText().trim();
		return (List) mapper.readValue(json, javaType);
	}
	
	public static void main(String[] args) throws Exception{
		getMockListData(RouteVO.class, "route/user/query");
	}
}
