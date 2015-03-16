/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.model;

import java.util.Set;

/**
 * Class for holding user data returned from Observant OADA Resource server.
 */
public class ObservantUser {

    private String username;
    private Set<String> scope;
    private Set<String> resourceIds;
    private String clientId;
    private String grantType;

    /**
     * Returns username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns scope.
     *
     * @return the scope
     */
    public Set<String> getScope() {
        return scope;
    }

    /**
     * Sets scope.
     *
     * @param scope the scope to set
     */
    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    /**
     * Returns resourceIds.
     *
     * @return the resourceIds
     */
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    /**
     * Sets resourceIds.
     *
     * @param resourceIds the resourceIds to set
     */
    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * Returns clientId.
     *
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets clientId.
     *
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Returns grantType.
     *
     * @return the grantType
     */
    public String getGrantType() {
        return grantType;
    }

    /**
     * Sets grantType.
     *
     * @param grantType the grantType to set
     */
    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ObservantUser [username=" + username + ", scope=" + scope + ", resourceIds=" + resourceIds
                + ", clientId=" + clientId + ", grantType=" + grantType + "]";
    }
}
