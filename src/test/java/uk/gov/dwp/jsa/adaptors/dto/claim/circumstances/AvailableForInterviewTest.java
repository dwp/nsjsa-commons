package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Test;
import uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.availability.Day;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AvailableForInterviewTest {

    private static final List<Day> DAYS_NOT_AVAILABLE = new ArrayList<>();

    @Test
    public void setDaysNotAvailable() {
        final AvailableForInterview availableForInterview = new AvailableForInterview();
        availableForInterview.setDaysNotAvailable(DAYS_NOT_AVAILABLE);
        assertThat(availableForInterview.getDaysNotAvailable(), is(DAYS_NOT_AVAILABLE));
    }

    @Test
    public void fieldsHaveDefaultValues() {
        final AvailableForInterview availableForInterview = new AvailableForInterview();
        assertThat(availableForInterview.getDaysNotAvailable(), is(DAYS_NOT_AVAILABLE));
    }
}