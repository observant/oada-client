/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.web;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.observant.oada.client.model.Portfolio;

/**
 * End points to query {@link Portfolio}s.
 */
@Controller
public class PortfolioController {

    @Value("${observant.oada.resources.url:http://localhost:9988/api/}")
    private String baseUrl;

    @Autowired
    private OAuth2RestOperations restTemplate;

    /**
     * Returns {@link Portfolio}s from the Observant OADA Resource server.
     *
     * @param principal current user in TestFarms server.
     * @return {@link Set } of {@link Portfolio}s
     */
    @RequestMapping(value = "/bookmarks/portfolios", method = RequestMethod.GET)
    @ResponseBody
    public Set<Portfolio> getObservantPortfolios(Principal principal) {
        Optional<Principal> opt = Optional.ofNullable(principal);
        String url = baseUrl + "bookmarks/portfolios";
        Portfolio[] portfolios = opt.map(p -> restTemplate.getForObject(url, Portfolio[].class)).orElseThrow(
                () -> new AccessDeniedException("Unauthorized"));
        Set<Portfolio> result = Optional.ofNullable(portfolios).map(this::toSet).orElse(Collections.emptySet());
        return result;
    }

    <T> Set<T> toSet(T[] array) {
        Assert.notNull(array, "array can not be null");
        return new HashSet<T>(Arrays.asList(array));
    }
}
