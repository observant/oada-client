/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * In memory implementation of {@link UserTokenStore}.
 * This class is thread safe and can be used from several threads at the same time.
 */
public class InMemoryUserTokenStore implements UserTokenStore {

    private final Map<String, OAuth2AccessToken> map = new HashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * @see au.com.observant.oada.client.service.UserTokenStore#put(java.lang.String,
     *      org.springframework.security.oauth2.common.OAuth2AccessToken)
     */
    @Override
    public OAuth2AccessToken put(String userId, OAuth2AccessToken token) {
        lock.writeLock().lock();
        try {
            return map.put(userId, token);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * @see au.com.observant.oada.client.service.UserTokenStore#getAll()
     */
    @Override
    public Map<String, OAuth2AccessToken> getAll() {
        lock.readLock().lock();
        try {
            Map<String, OAuth2AccessToken> copy = new HashMap<>(map);
            return copy;
        } finally {
            lock.readLock().unlock();
        }
    }
}
