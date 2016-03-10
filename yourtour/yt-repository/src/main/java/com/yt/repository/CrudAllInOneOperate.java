package com.yt.repository;

import com.yt.neo4j.Neo4jBaseBean;
import com.yt.neo4j.repository.CrudOperate;

public interface CrudAllInOneOperate<T extends Neo4jBaseBean> extends CrudOperate<T>, com.yt.hbase.CrudOperate {
}
