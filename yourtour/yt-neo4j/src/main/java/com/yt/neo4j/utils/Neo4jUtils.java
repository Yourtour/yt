package com.yt.neo4j.utils;

import org.neo4j.graphdb.Direction;

public class Neo4jUtils {

	public static Direction transformDirection(
			com.yt.neo4j.annotation.Neo4jRelationship.Direction direction) {
		switch (direction.name()) {
		case "INCOMING":
			return Direction.INCOMING;
		case "OUTGOING":
			return Direction.OUTGOING;
		default:
			return Direction.BOTH;
		}
	}

}
