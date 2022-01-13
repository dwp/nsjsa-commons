package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import org.junit.Test;
import uk.gov.dwp.jsa.adaptors.Constants;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PushStatusTypeTest {

    @Test
    public void hasCorrectValues() {
        assertThat(PushStatusType.values().length, is(5));
        assertThat(Arrays.asList(PushStatusType.values()), hasItems(
                PushStatusType.CANCELLED,
                PushStatusType.CLERICAL,
                PushStatusType.NOT_PUSHED,
                PushStatusType.PUSH_FAILED,
                PushStatusType.PUSHED));
    }

    @Test
    public void hasCorrectStatuasId() {
        assertThat(PushStatusType.CANCELLED.getStatusId(), is(Constants.LAST));
        assertThat(PushStatusType.CANCELLED.getStatusId(), is(Constants.LAST));
        assertThat(PushStatusType.CANCELLED.getStatusId(), is(Constants.LAST));
        assertThat(PushStatusType.CANCELLED.getStatusId(), is(Constants.LAST));
        assertThat(PushStatusType.CANCELLED.getStatusId(), is(Constants.LAST));

    }

}
