package com.yt.business.repository;

import com.yt.business.CrudAllInOneOperate;
import com.yt.business.bean.*;
import com.yt.business.common.Constants.GroupRole;

import java.util.List;

public interface RouteServiceRepository extends CrudAllInOneOperate {
    /**
     *
     * @param expertId
     * @return
     * @throws Exception
     */
    public List<ExpertServiceBean> getServices(Long expertId) throws Exception;
}
