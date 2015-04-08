/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.web;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.observant.oada.client.model.Sensor;

/**
 * End points to query {@link Sensor}s.
 */
@Controller
public class SensorController {

    @Value("${observant.oada.resources.url:http://localhost:9988/api/}")
    private String baseUrl;

    @Autowired
    private OAuth2RestOperations restTemplate;

    /**
     * Returns {@link Sensor}s from the Observant OADA Resource server.
     *
     * @param principal current user in TestFarms server.
     * @return {@link Set } of {@link Sensor}s
     */
    @RequestMapping(value = "/bookmarks/sensors", method = RequestMethod.GET)
    @ResponseBody
    public Set<Sensor> getObservantPortfolios(Principal principal) {
        Optional<Principal> opt = Optional.ofNullable(principal);
        String url = baseUrl + "bookmarks/sensors";

        @SuppressWarnings("unchecked")
        Set<Sensor> sensors = opt.map(p -> restTemplate.getForObject(url, Set.class)).orElseThrow(
                () -> new AccessDeniedException("Unauthorized"));
        return sensors;
    }
}
