package com.yt.business;

import com.yt.neo4j.repository.CrudOperate;

public interface CrudAllInOneOperate extends CrudOperate, com.yt.hbase.CrudOperate {
}
