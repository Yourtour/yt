package com.yt.dal.hbase.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ FIELD })
@Retention(RUNTIME)
public @interface HbaseColumn {
	String family() default "d";
	String name() default "";
	String version() default "1.0";
}
