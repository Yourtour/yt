package com.yt.dal.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

public class HbaseManager {

	@Autowired
	private HbaseTemplate template;
	private Configuration config;
	private Connection conn;
	protected Admin admin;

	public HbaseManager() {
		super();
	}

	protected void init() throws IOException {
		config = template.getConfiguration();
		conn = ConnectionFactory.createConnection(config);
		admin = conn.getAdmin();
	}

	public Configuration getConfiguration() throws IOException {
		if (config == null) {
			init();
		}
		return config;
	}

	public Connection getConnection() throws IOException {
		if (conn == null) {
			init();
		}
		return conn;
	}

	public Admin getAdmin() throws IOException {
		if (admin == null) {
			init();
		}
		return admin;
	}
	
	public boolean tableExist(String tableName) throws Exception {
		if (admin == null) {
			init();
		}
		return admin.tableExists(TableName.valueOf(tableName));
	}
	
}