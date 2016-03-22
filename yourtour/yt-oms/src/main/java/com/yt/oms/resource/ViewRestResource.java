package com.yt.oms.resource;

import com.yt.business.service.IViewService;
import com.yt.rest.resource.RestResource;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/21.
 */

@Component
@Path("/")
public class ViewRestResource extends RestResource {
    @Autowired
    private Configuration configuration;

    @Autowired
    private IViewService viewService;

    /**
     * 获取管理平台页面展现视图
     * @param name
     * @return
     * @throws Exception
     */
    @GET
    @Produces("text/html;charset=utf-8")
    @Path("admin/{name}")
    public Response getAdminResources(@PathParam("name") String name) throws Exception{
        String ftl = "admin/" + name + ".ftl";

        Map<String, Object> model = this.viewService.getValue(name);
        StringWriter out = new StringWriter();
        configuration.getTemplate(ftl).process(model, out);

        System.out.println(out.toString());
        return Response.ok().entity(out.toString()).build();
    }

    /**
     * 获取达人平台页面展现视图
     * @param name
     * @return
     * @throws Exception
     */
    @GET
    @Produces("text/html")
    @Path("expert/{name}")
    public Response getExpertResources(@PathParam("name") String name) throws Exception{
        String ftl = "expert/" + name + ".ftl";

        Map<String, Object> model = this.viewService.getValue(name);
        StringWriter out = new StringWriter();
        configuration.getTemplate(ftl).process(model, out);
        return Response.ok().entity(out.toString()).build();
    }
}
