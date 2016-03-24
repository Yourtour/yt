package com.yt.business.service;

import com.yt.business.bean.DictBean;

import java.util.List;

/**
 *
 * Created by 林平 on 2016/3/8.
 */
public interface IDictService {
    /**
     *
     * @param dict
     * @throws Exception
     */
    public void saveDictInfo(DictBean dict, Long userId) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    public List<DictBean> getDictInfoes() throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    public DictBean getDictInfo(Long dictid) throws Exception;

    /**
     *
     * @param id
     * @param userId
     * @throws Exception
     */
    public void deleteDictInfo(Long[] id, Long userId) throws Exception;
}
