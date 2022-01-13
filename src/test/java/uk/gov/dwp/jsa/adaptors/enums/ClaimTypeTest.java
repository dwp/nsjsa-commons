package uk.gov.dwp.jsa.adaptors.enums;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ClaimTypeTest {
    @Test
    public void hasCorrectValues() {
        assertThat(ClaimType.values().length, is(2));
        assertThat(Arrays.asList(ClaimType.values()), hasItems(
                ClaimType.EDIT_CLAIM,
                ClaimType.NEW_CLAIM));
    }
}
