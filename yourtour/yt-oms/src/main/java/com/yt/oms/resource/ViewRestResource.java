package com.yt.oms.resource;

import com.yt.rest.resource.RestResource;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/21.
 */

@Component
@Path("admin/")
public class ViewRestResource extends RestResource {
    @Autowired
    private Configuration configuration;

    /**
     * 获取页面展现视图
     * @param name
     * @return
     * @throws Exception
     */
    @GET
    @Produces("text/html")
    @Path("{name}")
    public Response getResources(@PathParam("name") String name) throws Exception{
        String ftl = name + ".ftl";
        Map<String, Object> model = new HashMap();
        model.put("name", name);
        StringWriter out = new StringWriter();
        configuration.getTemplate(ftl).process(model, out);
        return Response.ok().entity(out.toString()).build();
    }
}
