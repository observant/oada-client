/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Entry point for OADA specific pages.
 */
@Controller
public class OadaController {

    public static final String VIEW_OADA = "redirect:/oada/index.html";
    public static final String VIEW_HOME = "redirect:/index.html";

    private static final Logger log = LoggerFactory.getLogger(OadaController.class);

    @Value("${observant.oada.resources.url:http://localhost:9988/api/}")
    private String baseUrl;

    @Autowired
    private OAuth2RestOperations restTemplate;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/oada/", method = RequestMethod.GET)
    public String oadaEntryPoint() {
        String url = baseUrl + "users/me";
        try {
            // To initiate OAuth2 login process we are making request to known OADA Resources server end point.
            // Next line will redirect browser to Observant OADA OAuth2 server authorization flow if OAuth2 access token
            // is not available for the current local user.
            // Once authorization is completed OADA OAuth2 server redirects browser back to this method and we will call
            // the actual endpoint in Observant OADA Resource server.
            Map<String, String> result = restTemplate.getForObject(url, Map.class);
            log.debug("Response from OADA resource server: {}", result);
            // Next line will redirect browser to actual page in this server once user is authenticated by OAauth2
// server.
            return VIEW_OADA;
        } catch (UserDeniedAuthorizationException e) {
            log.error(e.toString());
            return VIEW_HOME;
        }
    }
}
