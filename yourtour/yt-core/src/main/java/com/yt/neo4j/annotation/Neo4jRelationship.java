package com.yt.neo4j.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ FIELD })
@Retention(RUNTIME)
public @interface Neo4jRelationship {
	public enum Direction {
		INCOMING, OUTGOING, BOTH
	};

	String relationship();

	Class<?> type();

	boolean isList() default false;

	Direction direction() default Direction.OUTGOING;
}
