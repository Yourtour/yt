package com.yt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer;

import java.util.Properties;

/**
 * Created by 林平 on 2016/2/29.
 */
public class PropertiesReader extends PreferencesPlaceholderConfigurer {
	private static final Log logger = LogFactory.getLog(PropertiesReader.class);

	private Properties pros = new Properties();

	public PropertiesReader() {
	}

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();

		try {
			super.loadProperties(pros);
		} catch (Exception exc) {
			logger.error("Exception Message.", exc);
		}
	}

	public String getProperty(String name) {
		if (pros.containsKey(name)) {
			return pros.getProperty(name);
		} else {
			return "";
		}
	}

	public String getProperty(String name, String defaultValue) {
		if (pros.containsKey(name)) {
			return pros.getProperty(name);
		} else {
			pros.setProperty(name, defaultValue);
			return getProperty(name);
		}
	}

	public void setProperty(String key, String property) {
		pros.setProperty(key, property);
	}

}
