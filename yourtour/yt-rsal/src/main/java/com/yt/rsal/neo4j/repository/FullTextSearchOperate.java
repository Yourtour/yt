package com.yt.rsal.neo4j.repository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

import com.yt.rsal.neo4j.bean.Neo4JBaseBean;

/**
 * 基于Neo4J的全文检索实现类。
 * 
 * <p>
 * <b>修改历史：</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2015年6月25日</td>
 * <td>john</td>
 * <td>Create</td>
 * </tr>
 * </table>
 * 
 * @author john
 * 
 * @version 1.0
 * @since 1.0
 */
@Component
public class FullTextSearchOperate implements IFullTextSearchOperate {

	/** 静态变量：系统日志 */
	private static final Log LOG = LogFactory
			.getLog(FullTextSearchOperate.class);

	@Autowired
	private Neo4jTemplate template;

	/**
	 * 默认构造方法
	 */
	public FullTextSearchOperate() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.rsal.neo4j.repository.IFullTextSearchOperate#query(java.lang.Class
	 * , com.yt.rsal.neo4j.repository.IFullTextSearchOperate.QueryTerm)
	 */
	@Override
	public List<Neo4JBaseBean> query(Class<? extends Neo4JBaseBean> clazz,
			QueryTerm term) throws Exception {
		String indexName = getIndexName(clazz);
		Index<Node> index = template.getIndex(indexName, clazz);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Start a full text search, clause: '%s:*%s*'.", term.key,
					term.value));
		}
		IndexHits<Node> result = index.query(term.key,
				String.format("*%s*", term.value));
		List<Neo4JBaseBean> list = new Vector<Neo4JBaseBean>();
		while (result.hasNext()) {
			Node node = result.next();
			Neo4JBaseBean bean = template.projectTo(node, clazz);
			list.add(bean);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Found %d row records.", list.size()));
		}
		return list;
	}

	private String getIndexName(Class<? extends Neo4JBaseBean> clazz)
			throws Exception {
		try {
			Field field = clazz.getDeclaredField("INDEX_NAME");
			if (field == null) {
				throw new Exception(String.format(
						"The Class[%s] not declare the 'INDEX_NAME'.",
						clazz.getName()));
			}
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			return (String) field.get(null);
		} catch (Exception ex) {
			throw new Exception(String.format(
					"The Class[%s] not declare the 'INDEX_NAME'.",
					clazz.getName()), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.rsal.neo4j.repository.IFullTextSearchOperate#query(java.lang.Class
	 * , java.util.List)
	 */
	@Override
	public List<Neo4JBaseBean> query(Class<? extends Neo4JBaseBean> clazz,
			List<QueryTerm> terms) throws Exception {
		return query(clazz, terms, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.rsal.neo4j.repository.IFullTextSearchOperate#query(java.lang.Class
	 * , java.util.List, boolean)
	 */
	@Override
	public List<Neo4JBaseBean> query(Class<? extends Neo4JBaseBean> clazz,
			List<QueryTerm> terms, boolean andJoin) throws Exception {
		String indexName = getIndexName(clazz);
		Index<Node> index = template.getIndex(indexName, clazz);
		String joinFlag = andJoin ? " AND " : " OR ";
		// composite the query string
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < terms.size(); i++) {
			if (i > 0) {
				sb.append(joinFlag);
			}
			QueryTerm term = terms.get(i);
			sb.append(term.key);
			sb.append(":");
			sb.append(String.format("*%s*", term.value));
		}
		String queryClause = sb.toString();
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Start a full text search, clause: '%s'.",
					queryClause));
		}
		IndexHits<Node> result = index.query(queryClause);
		List<Neo4JBaseBean> list = new Vector<Neo4JBaseBean>();
		while (result.hasNext()) {
			Node node = result.next();
			Neo4JBaseBean bean = template.projectTo(node, clazz);
			list.add(bean);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Found %d row records.", list.size()));
		}
		return list;
	}
}
