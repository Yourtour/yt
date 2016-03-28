package com.yt.oms.resource;

import com.sun.jersey.multipart.FormDataMultiPart;
import com.yt.business.bean.ActivityBean;
import com.yt.business.bean.ActivityContentBean;
import com.yt.business.service.IActivityService;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.StringUtils;
import com.yt.oms.vo.ActivityVO;
import com.yt.response.ResponseDataVO;
import com.yt.rest.resource.RestResource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

    @POST
    @Path("/intro/save")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ResponseDataVO<ActivityVO> saveActivityInfo(@QueryParam("activity") String activity,  FormDataMultiPart form) throws Exception{
        ActivityBean activityBean = BeanUtils.deserialize(activity, ActivityBean.class);

        String imageUrls = super.uploadMediaFile(form, "imageUrl", ACTIVITY_IMAGE_PATH);
        if(StringUtils.isNotNull(imageUrls)){
            activityBean.setImageUrl(imageUrls);
        }

        this.activityService.saveActivityInfo(activityBean, super.getCurrentUserId());

        ActivityVO vo = ActivityVO.transform(activityBean);
        return new ResponseDataVO<>(vo);
    }

    @POST
    @Path("/content/save")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ResponseDataVO<Long> saveActivityContentInfo(@QueryParam("content") String content,  FormDataMultiPart form) throws Exception{
        ActivityContentBean contentBean = BeanUtils.deserialize(content, ActivityContentBean.class);

        String imageUrls = super.uploadMediaFile(form, "imageUrl", ACTIVITY_IMAGE_PATH);
        if(StringUtils.isNotNull(imageUrls)){
            contentBean.setImageUrl(imageUrls);
        }

        this.activityService.saveActivityContentInfo(contentBean, super.getCurrentUserId());
        return new ResponseDataVO<>(contentBean.getId());
    }
}
