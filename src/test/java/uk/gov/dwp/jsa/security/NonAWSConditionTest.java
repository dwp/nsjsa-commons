package uk.gov.dwp.jsa.security;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NonAWSConditionTest {

    @Test
    public void testWhenTestShouldReturnTrue() {
        boolean result = NonAWSCondition.check(new String[]{"local_test", "pr1"});

        assertTrue("Should be true", result);
    }

    @Test
    public void testWhenDevShouldReturnFalse() {
        boolean result = NonAWSCondition.check(new String[]{"dev", "pr1"});

        assertFalse("Should be false", result);
    }

    @Test
    public void testWhenDevAndTestShouldReturnTrue() {
        boolean result = NonAWSCondition.check(new String[]{"dev", "local_test"});

        assertTrue("Should be true", result);
    }

    @Test
    public void testWhenNoDevOrTestShouldReturnTrue() {
        boolean result = NonAWSCondition.check(new String[]{"pr1"});

        assertFalse("Should be false", result);
    }
}
