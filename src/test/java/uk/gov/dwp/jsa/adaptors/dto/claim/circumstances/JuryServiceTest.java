package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class JuryServiceTest {

    public static final LocalDate START_DATE = LocalDate.now();
    public static final LocalDate END_DATE = LocalDate.now();

    @Test
    public void hasDefaultFieldValues() {
        final JuryService juryService = new JuryService();
        assertThat(juryService.getStartDate(), is(nullValue()));
        assertThat(juryService.getEndDate(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final JuryService juryService = new JuryService(START_DATE, END_DATE);
        assertThat(juryService.getStartDate(), is(START_DATE));
        assertThat(juryService.getEndDate(), is(END_DATE));
    }

    @Test
    public void setStartDate() {
        final JuryService juryService = new JuryService();
        juryService.setStartDate(START_DATE);
        assertThat(juryService.getStartDate(), is(START_DATE));
    }

    @Test
    public void setEndDate() {
        final JuryService juryService = new JuryService();
        juryService.setEndDate(END_DATE);
        assertThat(juryService.getEndDate(), is(END_DATE));
    }
}
