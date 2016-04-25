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
	 * 编辑推荐的键名
	 */
	public static final String KEY_BANNERS = "banners";
	/**
	 * 游徒推荐的键名
	 */
	public static final String KEY_YT_RECOMMENDS = "yt_recommend";
	/**
	 * 发现的键名
	 */
	public static final String KEY_DISCOVERS = "discover";

	/**
	 * 获取当前系统首页推荐内容.<br>
	 * 目前包括：编辑推荐、游徒推荐、发现。<br>
	 * 采用键值对方式返回数据，便于后续扩展。
	 * 
	 * @param userId
	 *            当前用户ID
	 * @param lastModifiedTime
	 *            上次访问的首页数据的修改时间
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getHomeData(Long userId, Long lastModifiedTime)
			throws Exception;

	public static final String KEY_LAUNCHBEAN = "launchBean";
	public static final String KEY_VERSIONBEAN = "versionBean";
	public static final String KEY_ACTIVITYBEAN = "activityBean";

	/**
	 * 客户端的运行访问
	 * 
	 * @param userId
	 *            当前访问用户ID
	 * @param accessToken
	 *            访问Token
	 * @param devType
	 *            设备类型
	 * @param appType
	 *            应用类型
	 * @param version
	 *            客户端版本号
	 * @return 客户端启动的基础信息，形式为键值对，包括：运行信息、版本信息、活动信息。
	 * @throws Exception
	 *             调用中发生的异常
	 */
	public Map<String, Object> launch(Long userId, String accessToken,
			String devType, String appType, String version) throws Exception;
}
