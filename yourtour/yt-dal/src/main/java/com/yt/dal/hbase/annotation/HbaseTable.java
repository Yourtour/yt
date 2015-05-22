package com.yt.dal.hbase.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE) 
@Retention(RUNTIME)
public @interface HbaseTable {
	String name() default "";
	String startKey() default "";
	String endKey() default "";
	int regionNum() default 1;
}