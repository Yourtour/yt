package com.yt.business.service;

import java.util.Map;

/**
 * 首页中相关的业务服务接口定义
 * 
 * @author John.Peng
 * 
 */
public interface IHomeService {
	/**
	 * 推荐资讯的键名
	 */
	public static final String KEY_BANNERS = "banners";
	/**
	 * 推荐行程的键名
	 */
	public static final String KEY_ROUTES = "routes";
	/**
	 * 大家都在玩的键名
	 */
	public static final String KEY_HOTPLAYINGS = "hotPlayings";

	/**
	 * 获取当前系统首页推荐内容.<br>
	 * 目前包括：推荐旅游资讯、推荐行程、推荐大家都在玩。<br>
	 * 采用键值对方式返回数据，便于后续扩展。
	 * @param userId  当前用户ID
	 * @param lastModifiedTime  上次访问的首页数据的修改时间
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getHomeData(Long userId, Long lastModifiedTime) throws Exception;

	public static final String KEY_LAUNCHBEAN = "launchBean";
	public static final String KEY_VERSIONBEAN = "versionBean";
	public static final String KEY_ACTIVITYBEAN = "activityBean";

	/**
	 * 客户端的运行访问
	 * 
	 * @param accessToken
	 *            访问Token
	 * @param version
	 *            客户端版本号
	 * @return 客户端启动的基础信息，形式为键值对，包括：运行信息、版本信息、活动信息。
	 */
	public Map<String, Object> launch(String accessToken, String devType, String appType, String version) throws Exception;
}
