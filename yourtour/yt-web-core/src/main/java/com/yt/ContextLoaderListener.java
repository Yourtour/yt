package com.yt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Created by 林平 on 2016/2/29.
 */
public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {
    private static final Log logger = LogFactory.getLog(ContextLoaderListener.class);

    public static String contextName = null;
    public static String realpath = null;

    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);

        ServletContext context = event.getServletContext();

        contextName = context.getServletContextName();
        realpath = context.getRealPath("/");

        logger.warn("Service run path=" + realpath);
    }
}
