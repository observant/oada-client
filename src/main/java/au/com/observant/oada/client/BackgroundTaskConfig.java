/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import au.com.observant.oada.client.service.InMemoryUserTokenStore;
import au.com.observant.oada.client.service.UserTokenStore;
import au.com.observant.oada.client.task.ScheduledTask;

/**
 * Background task related configuration.
 */
@Configuration
@EnableScheduling
public class BackgroundTaskConfig {

    /**
     * Returns UserTokenStore to use.
     *
     * @return new {@link UserTokenStore} instance
     */
    @Bean
    public UserTokenStore tokenHolder() {
        return new InMemoryUserTokenStore();
    }

    /**
     * Background task.
     *
     * @return new {@link ScheduledTask} instance
     */
    @Bean
    public ScheduledTask scheduledTask() {
        return new ScheduledTask();
    }

    /**
     * {@link OAuth2RestTemplate} for background tasks.
     * We can't use same {@link OAuth2RestTemplate} for the web requests and background tasks.
     * Still we can share {@link OAuth2ProtectedResourceDetails} to configure {@link OAuth2RestTemplate}s.
     *
     * @param resourceDetails {@link OAuth2ProtectedResourceDetails} to configure {@link OAuth2RestTemplate}
     * @return {@link OAuth2RestTemplate} to use in background tasks
     */
    @Bean(name = "backgroundOAuth2RestTemplate")
    public OAuth2RestTemplate restTemplateForBackgroundTask(OAuth2ProtectedResourceDetails resourceDetails) {
        return new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());
    }
}
