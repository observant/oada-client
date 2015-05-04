/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.web;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.observant.oada.client.model.Sensor;
import au.com.observant.oada.client.model.SensorSummary;

/**
 * End points to query {@link SensorSummary}s.
 */
@Controller
public class SensorController {

    @Value("${observant.oada.resources.url:http://localhost:9988/api/}")
    private String baseUrl;

    @Autowired
    private OAuth2RestOperations restTemplate;

    /**
     * Returns {@link SensorSummary}s from the Observant OADA Resource server.
     *
     * @param principal current user in TestFarms server.
     * @return {@link Set } of {@link SensorSummary}s
     */
    @RequestMapping(value = "/bookmarks/sensors", method = RequestMethod.GET)
    @ResponseBody
    public Set<SensorSummary> getSensors(
            @RequestParam(value = "portfolio", required = false) String portfolioId, Principal principal) {
        Optional<Principal> opt = Optional.ofNullable(principal);
        String url = baseUrl + "bookmarks/sensors";

        @SuppressWarnings("unchecked")
        Set<SensorSummary> sensors = opt.map(
                p -> restTemplate.getForObject(portfolioId == null ? url : (url + "?portfolio=" + portfolioId),
                        Set.class)).orElseThrow(() -> new AccessDeniedException("Unauthorized"));
        return sensors;
    }

    @RequestMapping(value = "/resources/{portfolio}/{sensor}/data", method = RequestMethod.GET)
    @ResponseBody
    public Sensor getSensor(@PathVariable("portfolio") String portfolio,
            @PathVariable("sensor") String sensor, Principal principal) {
        Validate.isTrue(StringUtils.isNotBlank(portfolio), "portfolio can not be blank");
        Validate.isTrue(StringUtils.isNotBlank(sensor), "sensor can not be blank");
        String url = baseUrl + "resources/" + portfolio + "/" + sensor + "/data";
        Sensor result = restTemplate.getForObject(url, Sensor.class);
        return result;
    }
}
