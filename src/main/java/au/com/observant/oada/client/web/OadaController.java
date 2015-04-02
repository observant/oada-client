/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.web;

import java.security.Principal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.observant.oada.client.service.UserTokenStore;

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

    @Autowired
    private UserTokenStore tokenStore;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/oada/", method = RequestMethod.GET)
    public String oadaEntryPoint(Principal principal) {
        String url = baseUrl + "users/me";
        try {
            // To initiate OAuth2 login process we are making request to known OADA Resources server endpoint.
            // Next line will redirect browser to Observant OADA OAuth2 server authorization flow if OAuth2 access token
            // is not available for the user in Observant OADA Client server (local user).
            // Once authorization is completed OADA OAuth2 server redirects browser back to this method and we will call
            // the actual endpoint in Observant OADA Resource server.
            Map<String, String> result = restTemplate.getForObject(url, Map.class);
            log.debug("Response from OADA resource server: {}", result);

            // At this point:
            // 1. User is authenticated in Observant OAuth2 server
            // 2. User has completed access grant process
            // 3. Observant OADA Client server has exchanged access code to actual access token
            // 4. Request to /api/users/me is completed successfully
            OAuth2AccessToken token = restTemplate.getAccessToken();

            // This is user ID in Observant OADA Client server (local user).
            String userId = principal.getName();

            // Storing OAuth2 access token to token store for later use.
            // This is only needed for our background process.
            tokenStore.put(userId, token);

            // Next line will redirect browser to actual OADA page in this server.
            return VIEW_OADA;
        } catch (UserDeniedAuthorizationException e) {
            log.error(e.toString());
            return VIEW_HOME;
        }
    }
}
