package com.yt.hbase.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 用于注解一个hbase实体类中的某一列，便于后续的数据访问操作。
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
@Target({ FIELD })
@Retention(RUNTIME)
public @interface HbaseColumn {
	/**
	 * 标记一个字段所属的列族名称，用于在初始建表。<br>
	 * <b>注意：</b>目前由于HBase的IO性能问题，一张表最好只有一个列族。
	 * 
	 * @return 字段所属的列族名称，默认为“d“。
	 */
	String family() default "d";

	/**
	 * 标记一个字段的名称
	 * 
	 * @return 字段名称，默认为空字符串
	 */
	String name() default "";

	/**
	 * 标记一个字段开始有效的版本号，所有字段中最大的版本号将成为该标的当前版本号。
	 * 
	 * @return 字段有效的版本号，表示在该版本之前将不存在该字段，默认为“1.0”。
	 */
	String version() default "1.0";
}
