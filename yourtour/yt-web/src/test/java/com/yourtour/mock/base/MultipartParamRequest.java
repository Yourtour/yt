package com.yourtour.mock.base;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * 封装带附件上传的请求接口
 * @author Tony.Zhang
 *
 */
public class MultipartParamRequest extends BaseRequest {
	private Map<String, String> values = new HashMap<String, String>();
	private List<MockMultipartFile> files = new ArrayList<MockMultipartFile>();
	
	public MultipartParamRequest(MockMvc mockMvc, String url) {
		super(mockMvc, Method.Post, url);
	}
	
	/**
	 * 
	 * @param name 第一个字段必须是一个Entity名称。系统自动将请求中的Json数据转换到对应的饿Entity
	 * @param content, 是个Json字符串
	 * @return
	 */
	public MultipartParamRequest addParameter(String name, String jsonContent){
		values.put(name, jsonContent);
		
		return this;
	}
	
	/**
	 * 
	 * @param name 第一个字段必须是一个Entity名称。系统自动将请求中的Json数据转换到对应的饿Entity
	 * @param content, 是个Json字符串
	 * @return
	 */
	public MultipartParamRequest addParameter(String jsonContent){
		return this.addParameter("data", jsonContent);
	}
	
	/**
	 * 
	 * @param fileName 必须和对象中字段对应。系统在将附件保存后，同时将保存文件名设置到对象中相应字段
	 * @param originalFileName
	 * @param contentType
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public MultipartParamRequest addFile(String fileName, String originalFileName, String contentType, InputStream is) throws Exception{
		MockMultipartFile  file = new MockMultipartFile (fileName,  originalFileName, contentType, is);
		files.add(file);
		return this;
	}
	
	@Override
	public void call() throws Exception {
		MockMultipartHttpServletRequestBuilder  builder = MockMvcRequestBuilders.fileUpload(url, new Object[]{});
		
		for(MockMultipartFile file : files){
			builder.file(file);
		}
		
		Set<String> paramNames = this.values.keySet();
		for(String paramName : paramNames){
			builder.param(paramName, values.get(paramName));
		}
		
		mockMvc.perform(builder).andDo(MockMvcResultHandlers.print());  
	}
	
	protected Object getValues(){
		return values;
	}
	
	/**
	 * Demo
	 * addFile()方法中的第一个参数必须和对象中字段对应。系统在将附件保存后，同时将保存文件名设置到对象中相应字段
	 * addparameter()方法中第一个字段必须是一个Entity名称。系统自动将请求中的Json数据转换到对应的饿Entity。
	public void authentication() throws Exception
	{
		MultipartRequestMock mocker=new MultipartRequestMock(this.mockMvc, "/MultipartPersist/User/Update.action");
		mocker.addFile("file", new FileInputStream("E:/tmp/T_AUTO_ATTACHMENTS.sql"));
		mocker.addFile("fil2e", new FileInputStream("E:/tmp/T_AUTO_ATTACHMENTS.sql"));
		
		UserEntity user = new UserEntity();
		user.setUserName("Tony");
		user.setUserId("111");
		mocker.addParameter("User",BeanUtils.writeValueAsString(user));
		
		mocker.call();
	}
	**/
}
