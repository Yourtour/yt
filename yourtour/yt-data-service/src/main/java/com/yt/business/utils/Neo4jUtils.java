package com.yt.business.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Neo4jUtils {
	private static final Log LOG = LogFactory.getLog(Neo4jUtils.class);

	public static long getGraphIDFromString(String id) {
		try {
			return Long.valueOf(id);
		} catch (Exception ex) {
			return -1;
		}
	}
}
