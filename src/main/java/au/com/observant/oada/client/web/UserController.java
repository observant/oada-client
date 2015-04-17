/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.web;

import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.observant.oada.client.model.ObservantUser;
import au.com.observant.oada.client.model.TestFarmsUser;

/**
 * End points to query user data.
 */
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Value("${observant.oada.resources.url:http://localhost:9988/api/}")
    private String baseUrl;

    @Autowired
    private OAuth2RestOperations restTemplate;

    /**
     * Returns user account related data from the Observant OADA Resource server.
     *
     * @param principal current user in TestFarms server.
     * @return {@link ObservantUser}
     */
    @RequestMapping(value = "/users/me", method = RequestMethod.GET)
    @ResponseBody
    public ObservantUser getObservantUser(Principal principal) {
        Optional<Principal> opt = Optional.ofNullable(principal);
        String url = baseUrl + "users/me";
        // If user is authenticated get user account data from the Observant OADA Resources server.
        // Otherwise send Unauthorized response.
        ObservantUser user = opt.map(p -> restTemplate.getForObject(url, ObservantUser.class)).orElseThrow(
                () -> new AccessDeniedException("Unauthorized"));
        log.info("Response from OADA resource server: {}", user);
        return user;
    }

    /**
     * Returns data related to local user account.
     *
     * @param principal current user in TestFarms server.
     * @return {@link TestFarmsUser}
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public TestFarmsUser getTestFarmsUser(Principal principal) {
        Optional<Principal> opt = Optional.ofNullable(principal);
        // If user is authenticated return local user account data.
        // Otherwise send Unauthorized response.
        TestFarmsUser user = opt.map(p -> new TestFarmsUser(p.getName())).orElseThrow(
                () -> new AccessDeniedException("Unauthorized"));
        return user;
    }
}
