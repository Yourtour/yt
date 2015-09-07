package com.yt.business.repository;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

import com.yt.business.bean.LineBean;
import com.yt.business.bean.SceneResourceBean;
import com.yt.business.neo4j.repository.RouteBeanRepository;
import com.yt.business.neo4j.repository.SceneResourceBeanRepository;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;
import com.yt.rsal.neo4j.repository.CrudGeneralOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate;
import com.yt.rsal.neo4j.repository.IFullTextSearchOperate.QueryTerm;

@Component
public class LineRespositoryImpl extends CrudGeneralOperate implements LineRepository {

	@Autowired
	private SceneResourceBeanRepository sceneRepo;

	@Autowired
	private IFullTextSearchOperate ftsOperate;

	@Autowired
	private Neo4jTemplate template;

	@Autowired
	private RouteBeanRepository routeRepo;

	@Override
	public List<LineBean> queryRecommandLine(String[] places, int dayNum,
			String[] scenes) throws Exception {
		// 不支持全文检索的查询
		int min = dayNum - 1, max = dayNum + 1;
		min = min >= 0 ? min : 0;
		min *= 24 * 3600;
		max *= 24 * 3600; // 换算为秒

		List<LineBean> recommendLines = routeRepo.query(places, min, max,
				scenes);
		return recommendLines;
	}

	public List<LineBean> queryRecommandLine2(String[] places, int dayNum,
			String[] scenes) throws Exception {
		// 首先对景点进行全文检索
		List<QueryTerm> terms = new Vector<QueryTerm>();
		for (String scene : scenes) {
			terms.add(new QueryTerm("name", scene));
		}
		List<Neo4JBaseBean> sceneBeans = ftsOperate.query(
				SceneResourceBean.class, terms, false);
		long[] ids = new long[sceneBeans.size()];
		for (int index = 0; index < ids.length; index++) {
			ids[index] = sceneBeans.get(index).getGraphId();
		}

		// 支持景点全文检索
		int min = dayNum - 1, max = dayNum + 1;
		min = min >= 0 ? min : 0;
		min *= 24 * 3600;
		max *= 24 * 3600; // 换算为秒

		List<LineBean> recommendLines = routeRepo.query(places, min, max, ids);
		return recommendLines;
	}
}