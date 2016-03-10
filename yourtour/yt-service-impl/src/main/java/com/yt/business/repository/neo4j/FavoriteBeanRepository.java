package com.yt.business.repository.neo4j;

import com.yt.business.bean.FavoriteBean;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface FavoriteBeanRepository extends GraphRepository<FavoriteBean> {

}
