/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.web;

import java.security.Principal;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.observant.oada.client.model.SensorMetadata;

/**
 *
 *
 */
@Controller
public class SensorMetadataController {

    @Value("${observant.oada.resources.url:http://localhost:9988/api/}")
    private String baseUrl;

    @Autowired
    private OAuth2RestOperations restTemplate;

    @RequestMapping(value = "/resources/{portfolio}/{sensor}/meta", method = RequestMethod.GET)
    @ResponseBody
    public SensorMetadata getMetadata(@PathVariable("portfolio") String portfolio,
            @PathVariable("sensor") String sensor, Principal principal) {
        Validate.isTrue(StringUtils.isNotBlank(portfolio), "portfolio can not be blank");
        Validate.isTrue(StringUtils.isNotBlank(sensor), "sensor can not be blank");
        String url = baseUrl + "resources/" + portfolio + "/" + sensor + "/meta";
        SensorMetadata metadata = restTemplate.getForObject(url, SensorMetadata.class);
        return metadata;
    }
}
