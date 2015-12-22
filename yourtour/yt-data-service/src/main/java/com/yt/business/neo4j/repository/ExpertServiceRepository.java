package com.yt.business.neo4j.repository;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.ExpertServiceBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface ExpertServiceRepository extends GraphRepository<ExpertServiceBean> {
	public List<ExpertServiceBean> getServices(Long expertId);
}
