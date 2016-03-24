package com.yt.business.service;

import java.util.List;

import com.yt.business.bean.BannerBean;

/**
 * 对相关Banner信息进行维护和统计查询的业务接口定义
 * 
 * @author John.Peng
 * 
 */
public interface IBannerService {
	/**
	 * 分页获取所有的Banner信息
	 * 
	 * @param nextCursor
	 *            当前开始游标
	 * @param limit
	 *            获取信息条数
	 * @return 符合条件的Banner信息列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<BannerBean> getBanners(Long nextCursor, int limit)
			throws Exception;

	/**
	 * 获取指定ID的banner信息
	 * 
	 * @param bannerId
	 *            Banner的ID
	 * @return Banner信息
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public BannerBean getBanner(Long bannerId) throws Exception;

	/**
	 * 保存指定的Banner信息
	 * 
	 * @param bean
	 *            Banner
	 * @param userId
	 *            操作用户ID
	 * @return 保存后的Banner
	 * @throws Exception
	 *             保存过程中发送的异常
	 */
	public BannerBean saveBanner(BannerBean bean, Long userId) throws Exception;

	/**
	 * 逻辑删除指定ID的Banner
	 * 
	 * @param bannerId
	 *            Banner的ID
	 * @param userId
	 *            操作用户ID
	 * @return Banner
	 * @throws Exception
	 *             删除过程中发生的异常
	 */
	public BannerBean deleteBanner(Long bannerId, Long userId) throws Exception;
}
