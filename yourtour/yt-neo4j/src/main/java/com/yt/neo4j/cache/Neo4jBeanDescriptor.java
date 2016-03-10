package com.yt.neo4j.cache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.utils.Neo4jUtils;

public class Neo4jBeanDescriptor {
	private static final Log LOG = LogFactory.getLog(Neo4jBeanDescriptor.class);

	private String name;
	private Map<String, RelationDescriptor> relations = null;

	public static Neo4jBeanDescriptor valueOf(Class<?> beanClass) {
		if (!beanClass.isAnnotationPresent(NodeEntity.class)) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String
						.format("The Neo4j bean class[%s] not be annotation the NoeEntity.",
								beanClass.getName()));
			}
			return null;
		}
		Neo4jBeanDescriptor nbd = new Neo4jBeanDescriptor();
		nbd.name = beanClass.getName();
		Class<?> clazz = beanClass;
		while (clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Neo4jRelationship relationship = field
						.getAnnotation(Neo4jRelationship.class);
				if (relationship == null) {
					continue;
				}
				if (LOG.isDebugEnabled()) {
					LOG.debug(String
							.format("Found a field[%s] be annotation the Neo4jRelationship.",
									field.getName()));
				}
				RelationDescriptor rd = nbd
						.createRelation(field.getName(), relationship
								.relationship(), relationship.type(),
								relationship.isList(), Neo4jUtils
										.transformDirection(relationship
												.direction()), field);
				nbd.getRelations().put(rd.getFieldName(), rd);
			}
			clazz = clazz.getSuperclass();
		}
		return nbd.getRelations().size() > 0 ? nbd : null;
	}

	public Neo4jBeanDescriptor() {
		relations = new HashMap<String, RelationDescriptor>();
	}

	public RelationDescriptor createRelation(String name, String relationship,
			Class<?> clazz, boolean isSet, Direction direction, Field field) {
		RelationDescriptor rd = new RelationDescriptor();
		rd.fieldName = name;
		rd.relationship = relationship;
		rd.clazz = clazz;
		rd.isSet = isSet;
		rd.direction = direction;
		rd.field = field;
		return rd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Neo4jBeanDescriptor clone() {
		Neo4jBeanDescriptor nbd = new Neo4jBeanDescriptor();
		nbd.name = this.getName();
		for (RelationDescriptor rd : this.getRelations().values()) {
			RelationDescriptor d = rd.clone();
			if (d == null) {
				continue;
			}
			nbd.getRelations().put(d.getFieldName(), d);
		}
		return nbd;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param fieldName
	 * @return
	 */
	public RelationDescriptor getRelation(String fieldName) {
		return relations.get(fieldName);
	}

	/**
	 * @return the relations
	 */
	public Map<String, RelationDescriptor> getRelations() {
		return relations;
	}

	public class RelationDescriptor {
		private String fieldName, relationship;
		private Class<?> clazz;
		private boolean isSet = false;
		private Direction direction = Direction.OUTGOING;
		private Field field;

		public RelationDescriptor() {
			super();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#clone()
		 */
		@Override
		public RelationDescriptor clone() {
			RelationDescriptor rd = new RelationDescriptor();
			rd.clazz = this.clazz;
			rd.fieldName = this.fieldName;
			rd.direction = this.direction;
			rd.isSet = this.isSet;
			rd.relationship = this.relationship;
			rd.field = this.field;
			return rd;
		}

		/**
		 * @return the fieldName
		 */
		public String getFieldName() {
			return fieldName;
		}

		/**
		 * @return the relationship
		 */
		public String getRelationship() {
			return relationship;
		}

		/**
		 * @return the clazz
		 */
		public Class<?> getClazz() {
			return clazz;
		}

		/**
		 * @return the isSet
		 */
		public boolean isSet() {
			return isSet;
		}

		/**
		 * @return the direction
		 */
		public Direction getDirection() {
			return direction;
		}

		/**
		 * @return the field
		 */
		public Field getField() {
			return field;
		}
	}
}
