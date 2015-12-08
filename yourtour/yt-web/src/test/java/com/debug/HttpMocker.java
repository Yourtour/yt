package com.debug;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.map.ObjectMapper;

import com.yt.vo.member.RegisterVO;


public class HttpMocker {
	private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public static void main(String[] args) throws Exception {  
    	doPost();
	}
	
    /**
     * Get 请求
     * @throws Exception
     */
	private static void doGet() throws Exception{
		HttpClient httpclient = new DefaultHttpClient();  
	    HttpGet httpgets = new HttpGet("http://localhost:8080/yt-web/users/account/register");    
	    HttpResponse response = httpclient.execute(httpgets);    
	    HttpEntity entity = response.getEntity();    
	    
	    getResponse(entity);
	}
	
	/**
	 * Post 请求
	 * @throws Exception
	 */
	private static void doPost() throws Exception{
		HttpClient httpclient = new DefaultHttpClient();  
	    HttpPost httpPost = new HttpPost("http://localhost:8080/yt-web/rest/users/account/register");
	    httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        
	    String json = getData();
        StringEntity se = new StringEntity(json);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(se);
        
	    HttpResponse response = httpclient.execute(httpPost);    
	    HttpEntity entity = response.getEntity();
	    getResponse(entity);
	}
	
	private static void getResponse(HttpEntity entity) throws Exception{
		if(entity == null){
			System.out.println("No Response");
			
			return;
		}
		
		// 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }

        // 将资料解码
        String reqBody = sb.toString();
        System.out.println(reqBody);
	}
	
	private static String getData() throws Exception{
		RegisterVO vo = new RegisterVO();
		
		vo.setMobile("13601951707");
		vo.setPassword("123456");
		
		ObjectMapper  objectMapper = new ObjectMapper();
		
		return objectMapper.writeValueAsString(vo);
	}
}	

