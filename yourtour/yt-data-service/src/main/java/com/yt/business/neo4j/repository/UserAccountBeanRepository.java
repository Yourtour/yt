/** 
 * @(#)UserBeanRepository.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.business.neo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.yt.business.bean.UserAccountBean;
import com.yt.business.bean.UserProfileBean;

/**
 * 关于用户实体（UserAccountBean）的关系查询接口定义。
 * 
 * <p>
 * <b>修改历史：</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2015年6月22日</td>
 * <td>john</td>
 * <td>Create</td>
 * </tr>
 * </table>
 * 
 * @author john
 * 
 * @version 1.0
 * @since 1.0
 */
public interface UserAccountBeanRepository extends GraphRepository<UserAccountBean> {

	/**
	 * 获取关注了相同行程的用户列表
	 * 
	 * @param user
	 *            　指定的用户
	 * @return　用户列表
	 */
	@Query("match n=(UserAccountBean{name:'{0}'}) return n")
	public UserAccountBean getUserAccountInfo(String userName);
}
