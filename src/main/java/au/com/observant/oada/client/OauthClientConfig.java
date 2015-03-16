/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Configuration for the OAuth2 client.
 */
@Configuration
@EnableOAuth2Client
public class OauthClientConfig {

    @Value("${observant.oada.authorization.url:http://localhost:9999/uaa/oauth/authorize}")
    private String authorizeUrl;

    @Value("${observant.oada.token.url:http://localhost:9999/uaa/oauth/token}")
    private String tokenUrl;

    @Value("${observant.oada.client.id}")
    private String clientId;

    @Value("${observant.oada.client.secret}")
    private String clientSecret;

    @Bean
    public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2RestTemplate(resource(), oauth2ClientContext);
    }

    @Bean
    protected OAuth2ProtectedResourceDetails resource() {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setAccessTokenUri(tokenUrl);
        resource.setUserAuthorizationUri(authorizeUrl);
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        return resource;
    }
}
