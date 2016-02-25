package com.yt.business.bean;

import com.yt.business.common.Constants;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.neo4j.graphdb.Direction;

/**
 * 旅行车，用来收集用户在目的地浏览过程中收集的和行程相关的资源或者达人
 * Created by 林平 on 2016/2/25.
 */
public class TravelCartBean {
    private Long subjectId;
    private Constants.ScheduleType type;

    public TravelCartBean(){
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Constants.ScheduleType getType() {
        return type;
    }

    public void setType(Constants.ScheduleType type) {
        this.type = type;
    }
}
