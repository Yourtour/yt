package com.yourtour.mock.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.test.web.servlet.MockMvc;

/**
 * 封装数组格式的请求参数
 * @author Tony.Zhang
 *
 */
public class ArrayParamRequest extends BaseRequest{
	private List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
	
	public ArrayParamRequest( MockMvc mockMvc, Method method, String url) {
		super(mockMvc, method, url);
	}
	
	public ArrayParamRequest addParameter(Map<String, Object> value){
		values.add(value);
		
		return this;
	}
	
	protected Object getValues(){
		return values;
	}
	
	/**
	 * Demo
	public void demo() throws Exception
	{
		ArrayRequestMocker mocker=new ArrayRequestMocker(this.mockMvc, ArrayRequestMocker.Method.Post, "/Persist/Station/Delete.action");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("stationId", "7c1409b2ae6c4858a11c640696a136a5");
		mocker.addParameter(map);
		mocker.call();
	}
	**/
}