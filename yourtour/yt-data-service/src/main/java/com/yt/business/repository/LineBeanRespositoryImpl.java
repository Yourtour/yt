package com.yt.business.repository;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.ResourceBean;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate.QueryTerm;

@Component
public class LineBeanRespositoryImpl implements LineBeanRepository {

	@Autowired
	private RouteBeanRepository routeRepo;

	@Autowired
	private IFullTextSearchOperate ftsOperate;

	@Override
	public List<LineBean> queryRecommandLine(String[] places, int dayNum,
			String[] scenes) throws Exception {
		// create the query terms
		List<QueryTerm> placeTerm = new Vector<QueryTerm>();
		for (String v : places) {
			placeTerm.add(new QueryTerm("plac", v));
		}
		List<QueryTerm> sceneTerm = new Vector<QueryTerm>();
		for (String v : scenes) {
			// TODO 根据景点实体对象中的列名修改Term的code。
			sceneTerm.add(new QueryTerm("name", v));
		}
		int min = dayNum - 1, max = dayNum + 1;
		min = min >= 0 ? min : 0;
		min *= 24 * 3600;
		max *= 24 * 3600; // 换算为秒

		// 从线路表中进行全文检索
		List<Neo4JBaseBean> result1 = ftsOperate.query(LineBean.class,
				placeTerm, false);
		StringBuilder sb = new StringBuilder();
		for (Neo4JBaseBean bean : result1) {
			sb.append(bean.getGraphId());
			sb.append(",");
		}
		// 根据查到的线路ID，采用图的搜索算法找到符合条件的行程，并返回关联的线路
		String ids = sb.substring(0, sb.length() - 1);
		List<LineBean> lines1 = routeRepo.findTheDayNumLine(ids, min, max);

		// 从资源表中进行全文检索
		List<Neo4JBaseBean> result2 = ftsOperate.query(ResourceBean.class,
				sceneTerm, false);
		sb = new StringBuilder();
		for (Neo4JBaseBean bean : result2) {
			sb.append(bean.getGraphId());
			sb.append(",");
		}
		// TODO 根据查到的景点ID，采用图的搜索算法找到符合条件的行程，并返回关联的线路
		ids = sb.substring(0, sb.length() - 1);
		List<LineBean> lines2 = null;

		// 合并搜索结果并返回
		lines1.addAll(lines2);
		return lines1;
	}
}
