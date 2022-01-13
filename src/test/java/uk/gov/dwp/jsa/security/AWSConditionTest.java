package uk.gov.dwp.jsa.security;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AWSConditionTest {

    @Test
    public void testWhenTestShouldReturnFalse() {
        boolean result = AWSCondition.check(new String[]{"local_test", "pr1"});

        assertFalse("Should be false", result);
    }

    @Test
    public void testWhenDevShouldReturnTrue() {
        boolean result = AWSCondition.check(new String[]{"dev", "pr1"});

        assertTrue("Should be true", result);
    }

    @Test
    public void testWhenNoDevOrTestShouldReturnTrue() {
        boolean result = AWSCondition.check(new String[]{"pr1"});

        assertTrue("Should be true", result);
    }
}
