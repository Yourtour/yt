package com.yt.dal.neo4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Neo4jSessionFactory {
	private static final Log LOG = LogFactory.getLog(Neo4jSessionFactory.class);
	private SessionFactory sessionFactory = null;

	public Neo4jSessionFactory() {
		super();
	}

	public Neo4jSessionFactory(String packagesString) {
		this();
		this.setPackages(packagesString);
	}

	public void setPackages(String packagesString) {
		if (packagesString == null || packagesString.isEmpty()) {
			throw new NullPointerException(
					"The packages string of the domains is null.");
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Initialize the session factory for Noe4J, pakcages: %s.",
					packagesString));
		}
		String[] packages = packagesString.split(",");
		sessionFactory = new SessionFactory(packages);
	}

	public Session openSession() {
		return sessionFactory.openSession();
	}

}
