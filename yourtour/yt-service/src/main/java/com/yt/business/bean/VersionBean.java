package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.hbase.annotation.HbaseTable;

/**
 * Created by 林平 on 2016/3/2.
 */
@HbaseTable(name = "T_APP_VERSION_INFO")
@NodeEntity
public class VersionBean  extends BaseBeanImpl {
}
