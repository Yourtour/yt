package com.yt.oms.resource;

import com.yt.business.service.IViewService;
import com.yt.rest.resource.RestResource;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
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
     * 获取Sitemesh 模板展现视图
     * @param request
     * @param module
     * @param name
     * @return
     * @throws Exception
     */
    @GET
    @Produces("text/html;charset=utf-8")
    @Path("/decorators/{module}/{name}.html")
    public Response getTemplateResource(@Context HttpServletRequest request, @PathParam("module") String module, @PathParam("name") String name) throws Exception{
        return this.getResource(request, module, name);
    }

    /**
     * 获取具体模块的页面展现视图
     * @param request
     * @param module
     * @param name
     * @return
     * @throws Exception
     */
    @GET
    @Produces("text/html;charset=utf-8")
    @Path("/{module}/{name}.html")
    public Response getAdminResources(@Context HttpServletRequest request, @PathParam("module") String module, @PathParam("name") String name) throws Exception{
        return this.getResource(request, module, name);
    }

    /**
     * 获取具体资源
     * @param request
     * @param module
     * @param name
     * @return
     * @throws Exception
     */
    private Response getResource(HttpServletRequest request, String module, String name) throws Exception{
        String ftl = module + "/" + name + ".ftl";

        Map<String, Object> model = this.viewService.getValue(name);
        if(model == null) model = new HashMap<>();

        Enumeration<String> names = request.getParameterNames();
        String paramName = null;
        while(names.hasMoreElements()){
            paramName = names.nextElement().toString();

            model.put(paramName, request.getParameter(paramName));
        }

        model.put("context", request.getContextPath());

        StringWriter out = new StringWriter();
        configuration.getTemplate(ftl).process(model, out);

        return Response.ok().entity(out.toString()).build();
    }
}
