package com.yt.oms.resource;

import com.yt.business.bean.DictBean;
import com.yt.business.service.IDictService;
import com.yt.core.common.StaticErrorEnum;
import com.yt.core.utils.CollectionUtils;
import com.yt.oms.vo.DictVO;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.rest.resource.RestResource;
import com.yt.utils.SessionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/22.
 */

@Component
@Path("admin/dicts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DictRestResource extends RestResource {
    private static final Log LOG = LogFactory.getLog(DictRestResource.class);

    @Autowired
    private IDictService service;

    /**
     * 保存字典数据
     * @param vo
     * @return
     * @throws Exception
     */
    @Path("/save")
    @POST
    public ResponseVO saveDictInfo(DictVO vo) throws Exception{
        if(vo == null){
            LOG.error("Input parameter is null when saving");

            return new ResponseVO(StaticErrorEnum.INPUT_IS_NULL);
        }

        DictBean bean = DictVO.transform(vo);

        this.service.saveDictInfo(bean, SessionUtils.getCurrentLoginUser());

        return new ResponseVO();
    }

    /**
     * 删除字典数据
     * @param dictIds
     * @return
     * @throws Exception
     */
    @Path("/{dictIds}/delete")
    @GET
    public ResponseVO deleteDictInfo(@PathParam("dictIds") String dictIds) throws Exception{
        if(dictIds == null){
            LOG.error("Input parameter is null when deleting");

            return new ResponseVO(StaticErrorEnum.INPUT_IS_NULL);
        }

        String[] sDictIds = dictIds.split(",");
        Long[] lDictIds = new Long[sDictIds.length];
        for(int index = 0; index < sDictIds.length; index++){
            lDictIds[index] = Long.valueOf(sDictIds[index]);
        }
        this.service.deleteDictInfo(lDictIds, SessionUtils.getCurrentLoginUser());

        return new ResponseVO();
    }

    /**
     * 获取所有的字典数据
     * @return
     * @throws Exception
     */
    @Path("/query")
    @POST
    public ResponseDataVO<List<DictVO>> getAllDictInfoes(Map<String, Object> params) throws Exception{
        List<DictVO> voes = new ArrayList<>();

        List<DictBean> beans = service.getDictInfoes();
        if(CollectionUtils.isNotEmpty(beans)){
            for(DictBean bean : beans){
                voes.add(DictVO.transform(bean));
            }
        }

        return new ResponseDataVO<>(voes);
    }

    /**
     * 获取所有的字典数据
     * @return
     * @throws Exception
     */
    @Path("/{dictId}")
    @GET
    public ResponseDataVO<DictVO> getDictInfo(@PathParam("dictId") Long dictId) throws Exception{
        DictVO vo = null;

        DictBean bean = service.getDictInfo(dictId);
        if(bean != null){
            vo = DictVO.transform(bean);
        }

        return new ResponseDataVO<>(vo);
    }
}


