package com.yt.oms.resource;

import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.yt.business.bean.ActivityBean;
import com.yt.business.service.IActivityService;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.StringUtils;
import com.yt.oms.vo.content.ActivityVO;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.rest.resource.RestResource;
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
 * 活动管理Restful接口
 * Created by 林平 on 2016/3/26.
 */
@Component
@Path("/oms/activity/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActivityRestResource extends RestResource {
    private static final Log LOG = LogFactory.getLog(ActivityRestResource.class);

    private static final String ACTIVITY_IMAGE_PATH = "/images/activities/";

    @Autowired
    private IActivityService activityService;

    /**
     * 活动查询
     * @param nextCursor
     * @param limit
     * @param total
     * @param params
     * @return
     * @throws Exception
     */
    @POST
    @Path("/query")
    public ResponseDataVO<List<ActivityVO>> queryActivityInfo(  @DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
                                                                @DefaultValue("20") @QueryParam("limit") int limit,
                                                                @QueryParam("total") int total, Map<String, Object> params)
    throws Exception{
        List<ActivityVO> voes = new ArrayList<>();

        List<ActivityBean> activities = activityService.queryActivityInfoes(nextCursor, limit, total, params);
        if(CollectionUtils.isNotEmpty(activities)){
            for(ActivityBean activity : activities){
                voes.add(ActivityVO.transform(activity));
            }
        }
        return new ResponseDataVO<>(voes);
    }

    /**
     * 保存活动基本信息
     * @param activity
     * @param multipart
     * @return
     * @throws Exception
     */
    @POST
    @Path("/save")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ResponseDataVO<ActivityVO> saveActivityInfo(@FormDataParam("activity") String activity,  FormDataMultiPart multipart) throws Exception{
        ActivityVO activityVO = BeanUtils.deserialize(activity, ActivityVO.class);

        ActivityBean activityBean = ActivityVO.transform(activityVO);

        String imageUrls = super.uploadMediaFile(multipart, "imageUrl", ACTIVITY_IMAGE_PATH);
        if(StringUtils.isNotNull(imageUrls)){
            activityBean.setImageUrl(imageUrls);
        }

        this.activityService.saveActivityInfo(activityBean, super.getCurrentUserId());

        ActivityVO vo = ActivityVO.transform(activityBean);
        return new ResponseDataVO<>(vo);
    }

    /**
     * 删除指定的活动数据
     * @param ids
     * @return
     * @throws Exception
     */
    @GET
    @Path("/{ids}/delete")
    public ResponseVO deleteActivityInfo(@PathParam("ids") String ids) throws Exception{
        if(StringUtils.isNull(ids)) return new ResponseVO();

        String[] sids = ids.split(VALUE_SEPERATOR);
        Long[] lids = new Long[sids.length];

        for(int index = 0; index < sids.length; index++){
            lids[index] = Long.valueOf(sids[index]);
        }

        this.activityService.deleteActivityInfo(lids, super.getCurrentUserId());
        return new ResponseVO();
    }

    /**
     * 获取指定活动信息
     * @param id
     * @return
     * @throws Exception
     */
    @GET
    @Path("/{id}")
    public ResponseDataVO<ActivityVO> getActivityInfo(@PathParam("id") Long id) throws Exception{
        ActivityBean activity = this.activityService.getActivityInfo(id);
        if(activity != null){
            return new ResponseDataVO<>(ActivityVO.transform(activity));
        }

        return new ResponseDataVO();
    }
}
