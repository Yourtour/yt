package com.yt.oms;

import com.yt.core.utils.StringUtils;
import freemarker.template.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 林平 on 2016/3/23.
 */
public class FreemarkerDecoratorServlet extends HttpServlet {
    private static final Log LOG = LogFactory.getLog(FreemarkerDecoratorServlet.class);

    private Configuration configuration;

    private String relativePath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            relativePath = config.getInitParameter("relativePath");
            if(StringUtils.isNull(relativePath)) relativePath = "admin";

            FactoryBean<Configuration> factoryBean =  WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean(FreeMarkerConfigurationFactoryBean.class);
            configuration = factoryBean.getObject();
        }catch(Exception exc){
            LOG.error("Error Message:", exc);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getRequestURI();
            name = name.substring(name.lastIndexOf("/") + 1);

            Map<String, Object> model = (Map<String, Object>) req.getAttribute("MODEL");
            if(model == null) model = new HashMap<>();

            model.put("context", req.getContextPath());
            PrintWriter pw = resp.getWriter();
            configuration.getTemplate(this.relativePath + "/" + name).process(model, pw);
        }catch(Exception exc){
            LOG.error("Error Message", exc);

            throw new IOException();
        }
    }
}
