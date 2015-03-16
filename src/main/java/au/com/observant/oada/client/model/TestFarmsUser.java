package au.com.observant.oada.client.model;

import au.com.observant.oada.client.WebSecurityConfig;

/**
 * Class for holding user data from the local application.
 *
 * @see WebSecurityConfig
 */
public class TestFarmsUser {

    private final String username;

    public TestFarmsUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
