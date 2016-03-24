package com.yt.vo;

import javax.ws.rs.FormParam;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/24.
 */
public class PaginationVO {
    private int total;

    private Long nextCursor;

    private int limit;

    private Map<String, Object> params;

    public PaginationVO(){}
    

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Long getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(Long nextCursor) {
        this.nextCursor = nextCursor;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
