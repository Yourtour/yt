package com.yt.hbase.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 用于注解一个hbase的实体类，便于后续能够比较自动化的处理相关的DDL和CRUD操作。
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
 * <td>2015年5月18日</td>
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
@Target(TYPE)
@Retention(RUNTIME)
public @interface HbaseTable {
	/**
	 * 标记表所属的命名空间，便于后续应用
	 * @return 命名空间，默认为“default”。
	 */
	String namespace() default "default";

	/**
	 * 标记表的名称，跟命名空间一起唯一标识一张表
	 * @return表名，默认为空字符串。
	 */
	String name() default "";

	/**
	 * 标记表分区的开始Key值，便于后续表数据分区
	 * @return分区开始的Key值，默认为空字符串，表示不分区。
	 */
	String startKey() default "";

	/**
	 * 标记表分区的结束Key值，便于后续表数据分区
	 * @return分区结束的Key值，默认为空字符串，表示不分区。
	 */
	String endKey() default "";

	/**
	 * 标记表包括的区域个数
	 * @return区域个数，默认为1。
	 */
	int regionNum() default 1;
}