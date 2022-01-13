package uk.gov.dwp.jsa.adaptors.enums;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserTypeTest {
    @Test
    public void hasCorrectValues() {
        assertThat(UserType.values().length, is(2));
        assertThat(Arrays.asList(UserType.values()), hasItems(
                UserType.AGENT,
                UserType.CITIZEN));
    }
}
