package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Test;
import uk.gov.dwp.jsa.adaptors.dto.claim.Address;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class PreviousWorkTest {

    public static final LocalDate START_DATE = LocalDate.now();
    public static final LocalDate END_DATE = LocalDate.now();
    public static final String REASON_ENDED = "REASON_ENDED";
    public static final String OTHER_REASON_DETAILS = "OTHER_REASON_DETAILS";
    public static final boolean PAYMENT_EXPECTED = true;
    public static final String EMPLOYER_NAME = "EMPLOYER_NAME";
    public static final boolean IS_SELF_EMPLOYED_OR_DIRECTOR = true;
    public static final String EMPLOYER_PHONE_NUMBER = "EMPLOYER_PHONE_NUMBER";
    public static final Address EMPLOYER_ADDRESS = new Address();

    @Test
    public void hasDefaultFieldValues() {
        final PreviousWork previousWork = new PreviousWork();

        assertThat(previousWork.getStartDate(), is(nullValue()));
        assertThat(previousWork.getEndDate(), is(nullValue()));
        assertThat(previousWork.getReasonEnded(), is(nullValue()));
        assertThat(previousWork.getOtherReasonDetails(), is(nullValue()));
        assertThat(previousWork.getPaymentExpected(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final PreviousWork previousWork = new PreviousWork(EMPLOYER_NAME, IS_SELF_EMPLOYED_OR_DIRECTOR, EMPLOYER_PHONE_NUMBER,
                EMPLOYER_ADDRESS, START_DATE, END_DATE, REASON_ENDED, OTHER_REASON_DETAILS, PAYMENT_EXPECTED);

        assertThat(previousWork.getStartDate(), is(START_DATE));
        assertThat(previousWork.getEndDate(), is(END_DATE));
        assertThat(previousWork.getReasonEnded(), is(REASON_ENDED));
        assertThat(previousWork.getOtherReasonDetails(), is(OTHER_REASON_DETAILS));
        assertThat(previousWork.getPaymentExpected(), is(PAYMENT_EXPECTED));

    }

    @Test
    public void setStartDate() {
        final PreviousWork previousWork = new PreviousWork();
        previousWork.setStartDate(START_DATE);
        assertThat(previousWork.getStartDate(), is(START_DATE));
    }

    @Test
    public void setEndDate() {
        final PreviousWork previousWork = new PreviousWork();
        previousWork.setEndDate(END_DATE);
        assertThat(previousWork.getEndDate(), is(END_DATE));
    }

    @Test
    public void setReasonEnded() {
        final PreviousWork previousWork = new PreviousWork();
        previousWork.setReasonEnded(REASON_ENDED);
        assertThat(previousWork.getReasonEnded(), is(REASON_ENDED));
    }

    @Test
    public void setOtherReasonDetails() {
        final PreviousWork previousWork = new PreviousWork();
        previousWork.setOtherReasonDetails(OTHER_REASON_DETAILS);
        assertThat(previousWork.getOtherReasonDetails(), is(OTHER_REASON_DETAILS));
    }

    @Test
    public void setPaymentExpected() {
        final PreviousWork previousWork = new PreviousWork();
        previousWork.setPaymentExpected(PAYMENT_EXPECTED);
        assertThat(previousWork.getPaymentExpected(), is(PAYMENT_EXPECTED));
    }
}