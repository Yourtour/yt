package com.yt.business.service.impl;

import com.yt.business.bean.DictBean;
import com.yt.business.service.IDictService;
import com.yt.neo4j.repository.CrudOperate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 林平 on 2016/3/22.
 */

@Service
public class DictServiceImpl extends ServiceBase implements IDictService {
    private static final Log LOG = LogFactory.getLog(DictServiceImpl.class);

    @Autowired
    private CrudOperate<DictBean> crudOperate;

    public DictServiceImpl() {
    }

    @Override
    public void saveDictInfo(DictBean dict, Long userId) throws Exception {
        this.updateBaseInfo(dict, userId);

        crudOperate.save(dict);
    }

    @Override
    public List<DictBean> getDictInfoes() throws Exception {
        return crudOperate.get();
    }

    @Override
    public void deleteDictInfo(Long[] ids, Long userId) throws Exception {
        for(Long id : ids) {
            DictBean bean = crudOperate.get(id);
            if (bean == null) {
                LOG.warn("No dict data found for id=" + id);
                return;
            }

            this.crudOperate.delete(bean);
        }
    }

    @Override
    public DictBean getDictInfo(Long dictid) throws Exception {
        return crudOperate.get(dictid);
    }
}
