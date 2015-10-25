package com.yt.neo4j.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.neo4j.graphdb.Direction;

@Target({ FIELD })
@Retention(RUNTIME)
public @interface Neo4jRelationship {
	String relationship();

	Class<?> type();

	boolean isList() default false;

	Direction direction() default Direction.OUTGOING;
}
