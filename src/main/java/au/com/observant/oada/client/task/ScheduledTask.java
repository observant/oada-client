/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.task;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import au.com.observant.oada.client.service.UserTokenStore;

/**
 * Scheduled task executed in the background to access OAuth2 protected resource without direct user interaction.
 */
public class ScheduledTask {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);

    @Value("${observant.oada.resources.url:http://localhost:9988/api/}")
    private String baseUrl;

    @Autowired
    private UserTokenStore tokenHolder;

    @Autowired
    @Qualifier("backgroundOAuth2RestTemplate")
    private OAuth2RestTemplate restTemplate;

    @Scheduled(fixedDelay = 30 * 1000L)
    public void requestDataForClients() {
        LOG.info("Executing scheduled task");
        String url = baseUrl + "/users/me";
        Map<String, OAuth2AccessToken> map = tokenHolder.getAll();
        map.entrySet().stream().forEach(entry -> {
            String userId = entry.getKey();
            OAuth2AccessToken token = entry.getValue();
            LOG.info("    Requesting data for user '{}' in background", userId);
            try {
                restTemplate.getOAuth2ClientContext().setAccessToken(token);
                String result = restTemplate.getForObject(url, String.class);
                LOG.info("    Response for '{}': '{}'", userId, result);
            } catch (Exception e) {
                LOG.warn("    Failed to request data for user '{}'", userId, e);
            }
        });
        LOG.info("Scheduled task completed");
    }
}
