package com.yt.business.repository.query;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.ResourceBean;
import com.yt.neo4j.repository.CrudOperate;

@Service
public class ResourceQuery implements IResourceQuery {
	@Autowired
	private CrudOperate<ResourceBean> resourceOperator;
	
	@Override
	public PagingDataBean<List<ResourceBean>> getResources(
			PagingConditionBean pagingCondition, Map<String, Object> params)
			throws Exception {
		StringBuffer sb = new StringBuffer();

		// 按目的地检索

		if (params.containsKey("placeId")) {
			sb.append("START n={placeId} MATCH n<-[:AT]-(resource:%s)");
		} else {
			sb.append("MATCH (resource:%s)");
		}

		String type = params.get("type").toString().toString().toUpperCase();
		sb.append(String.format(" WHERE resource.type=%s", type));
		// 按名字检索
		if (params.containsKey("name")) {
			sb.append(" AND resource.name like {name}");
		}

		// 按类型检索
		sb.append(" RETURN resource");
		List<ResourceBean> resources = this.resourceOperator
				.query(String.format(sb.toString(),
						ResourceBean.class.getSimpleName()), params);
		return new PagingDataBean<List<ResourceBean>>(10, resources);
	}
}