package uk.gov.dwp.jsa.adaptors.http.api;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class NotificationRequestTest {

    public static final UUID CLAIMANT_ID = UUID.randomUUID();

    @Test
    public void hasDefaultFieldValues() {
        final NotificationRequest request = new NotificationRequest();
        assertThat(request.getClaimantId(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final NotificationRequest request = new NotificationRequest(CLAIMANT_ID);
        assertThat(request.getClaimantId(), is(CLAIMANT_ID));
    }


    @Test
    public void setClaimantId() {
        final NotificationRequest request = new NotificationRequest();
        request.setClaimantId(CLAIMANT_ID);
        assertThat(request.getClaimantId(), is(CLAIMANT_ID));
    }
}
