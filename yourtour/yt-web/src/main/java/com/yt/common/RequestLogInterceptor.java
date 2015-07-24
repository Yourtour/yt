package com.yt.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 请求执行日志记录
 * @author Tony.Zhang
 *
 */
@Aspect
public class RequestLogInterceptor {
	private static Log logger = LogFactory.getLog(RequestLogInterceptor.class);
	
	@Around("@annotation(org.springframework.web.bind.annotation.ResponseBody)")  
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		Exception exc = null;
		Object obj = null;
		try{
			obj = pjp.proceed();
			
			return obj;
		}catch(Exception e){
			logger.error("Execution Exception.", e);
			exc = e;
			throw e;
		}finally{
			try{
				if(logger.isDebugEnabled()){
					MethodSignature signature = (MethodSignature) pjp.getSignature();
					StringBuffer sb = new StringBuffer("Request Logger:");
					sb.append("\nMethod=").append(signature.getName());
					Class<?>[] clazzes = signature.getParameterTypes();
					String[] params = signature.getParameterNames();
					Object[] values = pjp.getArgs();
					sb.append("\n\tInput Parameters:");
					
					logger.debug(sb.toString());
				}
			}catch(Exception e){}
		}
	}
}
