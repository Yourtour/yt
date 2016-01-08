package com.yt.business.neo4j.repository;

import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.ResourceActivityItemBean;
import com.yt.business.bean.RouteMainBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface ResourceActivityItemRepository extends GraphRepository<ResourceActivityItemBean> {
	@Query("START routeActivityItem=node({0}) MATCH routeActivityItem-[:RELATED]->(resourceActivityIten:ResourceActivityItemBean) RETURN resourceActivityIten")
	public List<ResourceActivityItemBean> getServices(Long[] routeActivityIds);
}
