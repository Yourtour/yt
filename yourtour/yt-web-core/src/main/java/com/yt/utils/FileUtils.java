package com.yt.utils;

import com.yt.ContextLoaderListener;
import com.yt.PropertiesReader;
import com.yt.core.utils.DateUtils;
import com.yt.core.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@Component
public class FileUtils implements ApplicationContextAware {
	private static final Log logger = LogFactory.getLog(FileUtils.class);

	public static String uploadBase = null;

	public static String getType(String name) {
		return name.substring(name.lastIndexOf(".") + 1);
	}

	/**
	 * 文件保存
	 * 
	 * @param relativePath
	 *            存储相对路径
	 * @param type
	 *            文件类型
	 * @param is
	 *            文件流
	 * @return
	 * @throws Exception
	 */
	public static String saveFile(String relativePath, String type,
			InputStream is) throws Exception {
		String date = DateUtils.formatDate(DateUtils.getCurrentTimeMillis(),
				"yyyyMMdd"), fileName = UUID.randomUUID().toString()
				.replaceAll("-", "")
				+ "." + type;
		if (!relativePath.startsWith(File.separator)) {
			relativePath = File.separator + relativePath;
		}
		if (!relativePath.endsWith(File.separator)) {
			relativePath = relativePath + File.separator;
		}
		relativePath = relativePath + date;
		if (!relativePath.endsWith(File.separator)) {
			relativePath = relativePath + File.separator;
		}
		relativePath = relativePath + fileName;
		String basePath = getUploadBase() + relativePath;
		File fPath = new File(basePath).getParentFile();
		if (!fPath.exists()) {
			fPath.mkdirs();
		}

		OutputStream os = new FileOutputStream(basePath);
		FileCopyUtils.copy(is, os);

		return relativePath;
	}

	/**
	 * 
	 * @param fileName
	 * @param is
	 * @throws Exception
	 */
	public static void saveFile(String fileName, InputStream is)
			throws Exception {
		String fullPath = getUploadBase() + File.separator + fileName;
		if (logger.isDebugEnabled()) {
			logger.debug("saving file=" + fullPath);
		}
		OutputStream os = new FileOutputStream(fullPath);
		FileCopyUtils.copy(is, os);
	}

	private static String getUploadBase() {
		return StringUtils.isNull(uploadBase) ? ContextLoaderListener.realpath
				: uploadBase;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if (applicationContext.containsBean("configProperties")) {
			PropertiesReader configProperties = applicationContext
					.getBean(PropertiesReader.class);

			uploadBase = configProperties.getProperty("app.upload.base");
		}
	}
}
