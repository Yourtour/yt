package com.yt.hbase;

/**
 * hbase字典实体类的接口定义。
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
 * <td>2015年９月２6日</td>
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
public interface BaseDictBean extends BaseBean {

	/**
	 * 获取代码
	 * 
	 * @return 代码
	 */
	public String getCode();

	/**
	 * 设置代码
	 * 
	 * @param code
	 *            代码
	 */
	public void setCode(String code);

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName();

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name);

}