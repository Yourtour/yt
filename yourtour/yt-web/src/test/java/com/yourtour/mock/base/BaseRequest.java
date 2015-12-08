package com.yourtour.mock.base;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class BaseRequest {
	public static enum Method {Post, Get};
	protected  MockMvc mockMvc;
	protected String url;
	private Method method;
	private Map<String, Object> session = new HashMap<String, Object>();
	
	public BaseRequest( MockMvc mockMvc, Method method, String url) {
		this.mockMvc = mockMvc;
		this.url = url;
		this.method = method;
	}
	
	protected String getJson(){
		try{
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(getValues());
			System.out.println(json);
			return json;
		}catch(Exception exc){
			exc.printStackTrace();
			return "";
		}
	}
	
	public BaseRequest setSessionAttr(String key, Object value){
		this.session.put(key,  value);
		
		return this;
	}
	
	public void call() throws Exception{
		MockHttpServletRequestBuilder builder = null;
		
		if(method.equals(Method.Post))
			builder = MockMvcRequestBuilders.post(url);
		else
			builder = MockMvcRequestBuilders.get(url);
		
		if(!session.isEmpty()){
			builder.sessionAttrs(session);
		}
		
		builder.contentType(MediaType.APPLICATION_JSON);
		builder.accept(MediaType.APPLICATION_JSON);
		
		builder.content(getJson());
		
		mockMvc.perform(builder).andDo(MockMvcResultHandlers.print());  
	}
	
	protected Object getValues(){
		return null;
	}

	/**
	 * Sample
	public void sample() throws Exception
	{
		RequestMocker mocker=new RequestMocker(this.mockMvc, RequestMocker.Method.Post, "/Persist/User/Update.action");
		mocker.addParameter("userId","09689b096cd542bfa5b03eb2540a51ac" )
		.addParameter("password", "99999");
		
		mocker.call();
	}
	**/
}