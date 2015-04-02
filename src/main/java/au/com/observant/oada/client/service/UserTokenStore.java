/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.service;

import java.util.Map;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * Service to store {@link OAuth2AccessToken}s.
 * Stored tokens can be used in background tasks to access OAuth2 resources.
 */
public interface UserTokenStore {

    /**
     * Stores {@link OAuth2AccessToken} for given userId.
     *
     * @param userId userId
     * @param token {@link OAuth2AccessToken} to store
     * @return previously stored {@link OAuth2AccessToken} for the userId or <tt>null</tt>
     */
    OAuth2AccessToken put(String userId, OAuth2AccessToken token);

    /**
     * Returns {@link Map} of userIds and {@link OAuth2AccessToken}s.
     *
     * @return {@link Map} of userIds and {@link OAuth2AccessToken}s
     */
    Map<String, OAuth2AccessToken> getAll();
}
