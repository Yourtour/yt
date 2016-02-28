package com.yt.core.utils;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class FileUtils {
	private static String baseDir = null;

	public static String getType(String name){
		return name.substring(name.lastIndexOf(".") + 1);
	}

	public static String saveFile(String baseDir, String type, InputStream is) throws Exception{
		String 	date = DateUtils.formatDate(DateUtils.getCurrentTimeMillis(), "yyyyMMdd"),
				fileName = UUID.randomUUID().toString().replaceAll("-","") + "." + type;

		String basePath = baseDir + File.separator + date;
		File fPath = new File(basePath);
		if(! fPath.exists()){
			fPath.mkdirs();
		}

		String fullPath = basePath  + File.separator + fileName;
		saveFile(fullPath, is);

		return date + File.separator + fileName;
	}

	public static void saveFile(String fileName, InputStream is) throws Exception{
		OutputStream os = new FileOutputStream(fileName);
		FileCopyUtils.copy(is, os);
	}
}
