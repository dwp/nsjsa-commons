package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import org.junit.Test;
import uk.gov.dwp.jsa.adaptors.Constants;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookingSubStatusTest {

    @Test
    public void hasCorrectValues() {
        assertThat(BookingSubStatus.values().length, is(13));
        assertThat(Arrays.asList(BookingSubStatus.values()), hasItems(
                BookingSubStatus.APPOINTEE_LETTER,
                BookingSubStatus.CALLBACK_1HR,
                BookingSubStatus.CALLBACK_2HR,
                BookingSubStatus.CALLBACK_3HR,
                BookingSubStatus.FAIL_TO_ATTEND,
                BookingSubStatus.LANDLINE_ONLY,
                BookingSubStatus.NO_VOICEMAIL,
                BookingSubStatus.PV_FLAG,
                BookingSubStatus.TOO_EARLY_FIRST_PERIOD,
                BookingSubStatus.TOO_EARLY_SECOND_PERIOD,
                BookingSubStatus.TOO_LATE,
                BookingSubStatus.VOICEMAIL,
                BookingSubStatus.WRONG_NINO));
    }

    @Test
    public void hasCorrectOrderId() {
        assertThat(BookingSubStatus.APPOINTEE_LETTER.getSubstatusOrderId(), is(Constants.LAST));
        assertThat(BookingSubStatus.CALLBACK_1HR.getSubstatusOrderId(), is(2));
        assertThat(BookingSubStatus.CALLBACK_2HR.getSubstatusOrderId(), is(3));
        assertThat(BookingSubStatus.CALLBACK_3HR.getSubstatusOrderId(), is(4));
        assertThat(BookingSubStatus.FAIL_TO_ATTEND.getSubstatusOrderId(), is(2));
        assertThat(BookingSubStatus.LANDLINE_ONLY.getSubstatusOrderId(), is(2));
        assertThat(BookingSubStatus.NO_VOICEMAIL.getSubstatusOrderId(), is(Constants.LAST));
        assertThat(BookingSubStatus.PV_FLAG.getSubstatusOrderId(), is(2));
        assertThat(BookingSubStatus.TOO_EARLY_FIRST_PERIOD.getSubstatusOrderId(), is(1));
        assertThat(BookingSubStatus.TOO_EARLY_SECOND_PERIOD.getSubstatusOrderId(), is(2));
        assertThat(BookingSubStatus.TOO_LATE.getSubstatusOrderId(), is(1));
        assertThat(BookingSubStatus.VOICEMAIL.getSubstatusOrderId(), is(Constants.LAST));
        assertThat(BookingSubStatus.WRONG_NINO.getSubstatusOrderId(), is(2));

    }
}
