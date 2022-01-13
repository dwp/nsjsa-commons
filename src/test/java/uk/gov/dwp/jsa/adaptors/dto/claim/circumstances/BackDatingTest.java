package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class BackDatingTest {



    private BackDating sut;

    @Before
    public void setup() {
        sut = new BackDating();
    }

    @Test
    public void setWhyNotApplySooner() {
        String actual = "SOONER";
        sut.setWhyNotApplySooner(actual);
        String expected = sut.getWhyNotApplySooner();

        assertThat(expected, is(actual));
    }

    @Test
    public void setWereYouAvailableForWork() {
        Boolean actual = true;
        sut.setWereYouAvailableForWork(actual);
        Boolean expected = sut.getWereYouAvailableForWork();
        assertThat(expected, is(actual));
    }

    @Test
    public void setWereYouSearchingForWork() {
        Boolean actual = true;
        sut.setWereYouSearchingForWork(actual);
        Boolean expected = sut.getWereYouSearchingForWork();
        assertThat(expected, is(actual));
    }

    @Test
    public void setInPaidWorkSince() {
        Boolean actual = true;
        sut.setInPaidWorkSince(actual);
        Boolean expected = sut.getInPaidWorkSince();

        assertThat(expected, is(actual));
    }

    @Test
    public void setInFullTimeEducationSince() {
        Boolean actual = true;
        sut.setInFullTimeEducationSince(actual);
        Boolean expected = sut.getInFullTimeEducationSince();

        assertThat(expected, is(actual));
    }

    @Test
    public void setAskedForAdvice() {
        String actualAdvice = "ADVICE";
        Boolean actualHasAsked = true;
        AskedForAdvice actual = new AskedForAdvice();
        actual.setHasAsked(actualHasAsked);
        actual.setAdvice(actualAdvice);

        sut.setAskedForAdvice(actual);
        AskedForAdvice expected = sut.getAskedForAdvice();

        assertThat(expected.getAdvice(), is(actualAdvice));
        assertThat(expected.getHasAsked(), is(actualHasAsked));
    }

    @Test
    public void setNonWorkingIllness() {
        Boolean actualHadIllness = true;
        LocalDate actualStartDate = LocalDate.of(2020, 9, 2);
        LocalDate actualEndDate = actualStartDate.plusDays(3);

        NonWorkingIllness actual = new NonWorkingIllness();
        actual.setHadIllness(actualHadIllness);
        actual.setStartDate(actualStartDate);
        actual.setEndDate(actualEndDate);

        sut.setNonWorkingIllness(actual);
        NonWorkingIllness expected = sut.getNonWorkingIllness();

        assertThat(expected.getHadIllness(), is(actualHadIllness));
        assertThat(expected.getStartDate(), is(actualStartDate));
        assertThat(expected.getEndDate(), is(actualEndDate));
    }

    @Test
    public void setTravelledOutsideUk() {
        Boolean actualHadTravelled = true;
        LocalDate actualStartDate = LocalDate.of(2020, 9, 2);
        LocalDate actualEndDate = actualStartDate.plusDays(3);

        TravelledOutsideUk actual = new TravelledOutsideUk();
        actual.setHadTravelled(actualHadTravelled);
        actual.setStartDate(actualStartDate);
        actual.setEndDate(actualEndDate);

        sut.setTravelledOutsideUk(actual);
        TravelledOutsideUk expected = sut.getTravelledOutsideUk();

        assertThat(expected.getHadTravelled(), is(actualHadTravelled));
        assertThat(expected.getStartDate(), is(actualStartDate));
        assertThat(expected.getEndDate(), is(actualEndDate));
    }

    @Test
    public void fieldsHaveDefaultValues() {
        AskedForAdvice askedForAdvice = new AskedForAdvice(null, null);
        NonWorkingIllness nonWorkingIllness = new NonWorkingIllness(null, null, null);
        TravelledOutsideUk travelledOutsideUk = new TravelledOutsideUk(null, null, null);

        sut = new BackDating(
                null,
                null,
                null,
                null,
                null,
                askedForAdvice,
                nonWorkingIllness,
                travelledOutsideUk
        );

        assertThat(sut.getWhyNotApplySooner(), is(nullValue()));
        assertThat(sut.getInPaidWorkSince(), is(nullValue()));
        assertThat(sut.getInFullTimeEducationSince(), is(nullValue()));
        assertThat(sut.getAskedForAdvice(), is(askedForAdvice));
        assertThat(sut.getNonWorkingIllness(), is(nonWorkingIllness));
        assertThat(sut.getTravelledOutsideUk(), is(travelledOutsideUk));
    }

}
