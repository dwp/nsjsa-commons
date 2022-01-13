package uk.gov.dwp.jsa.security;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class UserTest {
    public static final String STAFF_NUMBER = "STAFF_NUMBER";
    public static final String FULL_NAME = "FULL_NAME";
    public static final String PAYLOAD = "PAYLOAD";

    @Test
    public void constructorSetsSomeFields() {
        final User user = new User(FULL_NAME, STAFF_NUMBER);
        assertThat(user.getFullName(), is(FULL_NAME));
        assertThat(user.getStaffNumber(), is(STAFF_NUMBER));
        assertThat(user.getPayload(), is(nullValue()));
    }

    @Test
    public void constructorSetsAllFields() {
        final User user = new User(FULL_NAME, STAFF_NUMBER, PAYLOAD);
        assertThat(user.getFullName(), is(FULL_NAME));
        assertThat(user.getStaffNumber(), is(STAFF_NUMBER));
        assertThat(user.getPayload(), is(PAYLOAD));
    }
}
