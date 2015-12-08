package com.yourtour.mock.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.test.web.servlet.MockMvc;

/**
 * 封装一般Key-Value格式的请求参数
 * @author Tony.Zhang
 *
 */
public class SimpleParamRequest extends BaseRequest {
	private Map<String, Object> values = new HashMap<String, Object>();
	
	public SimpleParamRequest( MockMvc mockMvc, Method method, String url) {
		super(mockMvc, method, url);
	}
	
	public SimpleParamRequest addParameter(String name, Object value){
		values.put(name, value);
		
		return this;
	}
	
	protected Object getValues(){
		return values;
	}
	
	/**
	 * Demo
	 * addFile()方法中的第一个参数必须和对象中字段对应。系统在将附件保存后，同时将保存文件名设置到对象中相应字段
	 * addparameter()方法中第一个字段必须是一个Entity名称。系统自动将请求中的Json数据转换到对应的饿Entity。
	public void demo() throws Exception
	{
		SimpleRequestMocker mocker=new SimpleRequestMocker(this.mockMvc, SimpleRequestMocker.Method.Post, "/User/Password/Update.action");
		mocker.addParameter("userId", "f9f9aeb2abfb45dba0d9dc581eedd6d4")
		.addParameter("oldPwd","50511")
		.addParameter("newPwd", "2334545")
		.addParameter("code", "8787");
		mocker.call();
	}
	**/
}