package com.yt.neo4j.repository;

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

import com.yt.neo4j.Neo4jBaseBean;

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
public class FullTextSearchGeneralOperate implements FullTextSearchOperate {

	/** 静态变量：系统日志 */
	private static final Log LOG = LogFactory
			.getLog(FullTextSearchGeneralOperate.class);

	@Autowired
	private Neo4jTemplate template;

	/**
	 * 默认构造方法
	 */
	public FullTextSearchGeneralOperate() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.rsal.neo4j.repository.IFullTextSearchOperate#search(java.lang.
	 * Class , com.yt.rsal.neo4j.repository.IFullTextSearchOperate.SearchTerm)
	 */
	@Override
	public List<Neo4jBaseBean> search(Class<? extends Neo4jBaseBean> clazz,
			SearchTerm term) throws Exception {
		String indexName = getIndexName(clazz);
		Index<Node> index = template.getIndex(indexName, clazz);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Start a full text search, clause: '%s:*%s*'.", term.key,
					term.value));
		}
		IndexHits<Node> result = index.query(term.key,
				String.format("*%s*", term.value));
		List<Neo4jBaseBean> list = new Vector<Neo4jBaseBean>();
		while (result.hasNext()) {
			Node node = result.next();
			Neo4jBaseBean bean = template.projectTo(node, clazz);
			list.add(bean);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Found %d row records.", list.size()));
		}
		return list;
	}

	private String getIndexName(Class<? extends Neo4jBaseBean> clazz)
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
	 * com.yt.rsal.neo4j.repository.IFullTextSearchOperate#search(java.lang.Class
	 * , java.util.List)
	 */
	@Override
	public List<Neo4jBaseBean> search(Class<? extends Neo4jBaseBean> clazz,
			List<SearchTerm> terms) throws Exception {
		return search(clazz, terms, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.rsal.neo4j.repository.IFullTextSearchOperate#search(java.lang.Class
	 * , java.util.List, boolean)
	 */
	@Override
	public List<Neo4jBaseBean> search(Class<? extends Neo4jBaseBean> clazz,
			List<SearchTerm> terms, boolean andJoin) throws Exception {
		String indexName = getIndexName(clazz);
		Index<Node> index = template.getIndex(indexName, clazz);
		String joinFlag = andJoin ? " AND " : " OR ";
		// composite the query string
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < terms.size(); i++) {
			if (i > 0) {
				sb.append(joinFlag);
			}
			SearchTerm term = terms.get(i);
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
		List<Neo4jBaseBean> list = new Vector<Neo4jBaseBean>();
		while (result.hasNext()) {
			Node node = result.next();
			Neo4jBaseBean bean = template.projectTo(node, clazz);
			list.add(bean);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Found %d row records.", list.size()));
		}
		return list;
	}
}
