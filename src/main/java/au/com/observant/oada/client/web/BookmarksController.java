/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.web;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.observant.oada.client.model.Bookmark;

/**
 *
 */
@Controller
public class BookmarksController {

    @Value("${observant.oada.resources.url:http://localhost:9988/api/}")
    private String baseUrl;

    @Autowired
    private OAuth2RestOperations restTemplate;

    @RequestMapping(value = "/bookmarks", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Bookmark> get() {
        String url = baseUrl + "/bookmarks";
        Bookmark[] result = restTemplate.getForObject(url, Bookmark[].class);
        return Arrays.asList(result);
    }
}
