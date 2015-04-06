/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.web;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.observant.oada.client.model.ObservantUser;
import au.com.observant.oada.client.model.Portfolio;

/**
 * End points to query user data.
 */
@Controller
public class PortfolioController {

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
    @RequestMapping(value = "/bookmarks/portfolios", method = RequestMethod.GET)
    @ResponseBody
    public Set<Portfolio> getObservantPortfolios(Principal principal) {
        log.debug("getObservantPortfolio :");
        Optional<Principal> opt = Optional.ofNullable(principal);
        String url = baseUrl + "bookmarks/portfolios";
        // If user is authenticated get user account data from the Observant OADA Resources server.

        @SuppressWarnings("unchecked")
        Set<Portfolio> portfolios = opt.map(p -> restTemplate.getForObject(url, Set.class)).orElseThrow(
                () -> new AccessDeniedException("Unauthorized"));
        log.info("Response from OADA resource server: {}", portfolios);
        return portfolios;
    }

    /**
     * Exception handler for {@link AccessDeniedException}.
     * Maps {@link AccessDeniedException} to HTTP response 401.
     *
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void exceptionHandler(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}
