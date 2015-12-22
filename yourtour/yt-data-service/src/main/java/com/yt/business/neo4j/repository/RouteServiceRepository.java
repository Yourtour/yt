package com.yt.business.neo4j.repository;

import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.RouteServiceBean;
import com.yt.business.bean.UserProfileBean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface RouteServiceRepository extends GraphRepository<RouteServiceBean> {


}
