package com.yt.rest.resource;

/**
 * 所有基于Jersey Restful 接口的基础类。每个具体接口定义时注意以下几点：
 * 1）每个接口方法必须抛出异常，然后有应用统一的异常处理AOP接口进行捕获并处理，
 * 2）在给接口中，涉及到数据修改保存的接口，第一步必须先从数据存储中获取已经存在的数据，然后根据每个接口提交的数据进行修改，防止数据破坏
 *
 */
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.yt.utils.FileUtils;
import com.yt.utils.SessionUtils;
import com.yt.utils.WebUtils;

public class RestResource {
	public static final String FILE_SEPERATOR = ","; // 多文件存储时，文件名之间的分隔符
	public static final String VALUE_SEPERATOR = ","; // 多值分隔符

	public RestResource() {
		super();
	}

	protected String getCurrentAccess(HttpServletRequest request)
			throws Exception {
		return request.getHeader("Access-Token");
	}

	protected String getCurrentAccess() throws Exception {
		return getCurrentAccess(WebUtils.getHttpServletRequest());
	}

	protected Long getCurrentUserId(HttpServletRequest request)
			throws Exception {
		return SessionUtils.getCurrentLoginUser();
	}

	protected Long getCurrentUserId() throws Exception {
		return SessionUtils.getCurrentLoginUser();
	}

	/**
	 * 通过FORM方式上传媒体文件
	 * 
	 * @param form
	 *            FORM
	 * @param fieldName
	 *            FORM中的字段名
	 * @param rootPath
	 *            媒体文件存储的路径
	 * @return 返回媒体文件存储的全路径，没有存储文件返回null。
	 * @throws Exception
	 *             存储过程中发生的异常
	 */
	protected String uploadMediaFile(FormDataMultiPart form, String fieldName,
			String rootPath) throws Exception {
		StringBuffer images = new StringBuffer();
		List<FormDataBodyPart> l = form.getFields(fieldName);
		if (l != null) {
			for (FormDataBodyPart p : l) {
				InputStream is = p.getValueAs(InputStream.class);
				FormDataContentDisposition detail = p
						.getFormDataContentDisposition();
				if(images.length() > 0) images.append(FILE_SEPERATOR);

				images.append(FileUtils.saveFile(rootPath, FileUtils.getType(detail.getFileName()), is));
			}
		}

		return images.toString();
	}
}
