package com.yt.rest.resource;

import com.yt.business.bean.ResourceBean;
import com.yt.business.common.Constants;
import com.yt.business.service.IResourceService;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.utils.SessionUtils;
import com.yt.vo.resource.ResourceVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("app/resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceRestResource {
    private static final Log LOG = LogFactory
            .getLog(ResourceRestResource.class);

    @Autowired
    private IResourceService resourceService;

    /**
     * 获取指定资源信息
     * @param resourceId
     * @param type
     * @return
     * @throws Exception
     */
    @Path("/{resourceId}/type/{type}")
    @GET
    public ResponseDataVO<ResourceVO> getResource(@PathParam("resourceId") Long resourceId,
                                                         @PathParam("type") String type) throws Exception {
        Constants.ResType resType = Constants.ResType.getEnum(type);

        ResourceBean resource = resourceService.getResource(resourceId, resType);
        ResourceVO vo = ResourceVO.transform(resource);

        return new ResponseDataVO<ResourceVO>(vo);
    }
    /**
     * 保存资源资源
     * @param resourceId
     * @param type
     * @return
     * @throws Exception
     */
    @Path("/{resourceId}/type/{type}/save")
    @POST
    public ResponseVO saveResource(@PathParam("resourceId") Long resourceId,
                                     @PathParam("type") String type, ResourceVO vo) throws Exception {
        Constants.ResType resType = Constants.ResType.getEnum(type);

        ResourceBean resource = ResourceVO.transform(vo);
        resourceService.saveResource(resource, resType, SessionUtils.getCurrentLoginUser());

        return new ResponseVO();
    }


    /**
     * 删除资源
     * @param resourceId
     * @param type
     * @return
     * @throws Exception
     */
    @Path("/{resourceId}/type/{type}/delete")
    @GET
    public ResponseVO deleteResource(@PathParam("resourceId") Long resourceId,
                                                  @PathParam("type") String type) throws Exception {
        Constants.ResType resType = Constants.ResType.getEnum(type);

        resourceService.deleteResource(resourceId, resType, SessionUtils.getCurrentLoginUser());

        return new ResponseVO();
    }

    /**
     * 获取目的地资源
     * @param placeId
     * @return
     * @throws Exception
     */
    @Path("/place/{placeId}/type/{type}")
    @GET
    public ResponseDataVO<List<ResourceVO>> getResources(@PathParam("placeId") Long placeId,
                                                         @PathParam("type") String type) throws Exception {
        List<ResourceVO> list = new ArrayList<>();
        Constants.ResType resType = Constants.ResType.getEnum(type);

        List<? extends ResourceBean> result = resourceService.getResources(placeId, 0l, 20, resType);
        for (ResourceBean bean : result) {
            if (bean == null) {
                continue;
            }
            list.add(ResourceVO.transform(bean));
        }

        return new ResponseDataVO<List<ResourceVO>>(list);
    }
}
