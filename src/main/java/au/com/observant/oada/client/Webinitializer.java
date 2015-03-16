/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Entry point for web application running in servlet container - Tomcat, Jetty, etc.
 */
public class Webinitializer extends SpringBootServletInitializer {

    /**
     * @see org.springframework.boot.context.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
