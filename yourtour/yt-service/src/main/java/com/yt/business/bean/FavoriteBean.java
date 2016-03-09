package com.yt.business.bean;

import com.yt.business.BusinessBeanImpl;
import com.yt.business.CommentBaseBean;
import com.yt.business.FavoriteBaseBean;
import com.yt.hbase.annotation.HbaseTable;
import org.springframework.data.neo4j.annotation.NodeEntity;

@HbaseTable(name = "T_COMMENT_INFO")
@NodeEntity
public class FavoriteBean extends BusinessBeanImpl {
	private static final long serialVersionUID = -2639574489334772005L;

	private transient FavoriteBaseBean bean;

	public FavoriteBean() {
	}

	public FavoriteBaseBean getBean() {
		return bean;
	}

	public void setBean(FavoriteBaseBean bean) {
		this.bean = bean;
	}
}
