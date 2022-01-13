package uk.gov.dwp.jsa.adaptors.dto.claim.validation;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PushClaimResponseTest {

    public static final boolean CONTACT_UPDATE_FAILURE = false;
    public static final boolean POSTAL_ADDRESS_UPDATE_FAILURE = false;
    public static final boolean NAME_UPDATE_FAILURE = false;
    public static final boolean CORRESPONDANCE_ADDRESS_UPDATE_FAILURE = false;

    @Test
    public void isSuccessfulReturnsTrue() {
        final PushClaimResponse response = new PushClaimResponse();
        assertThat(response.isSuccessful(), is(true));

    }

    @Test
    public void hasDefaultFieldValues() {
        final PushClaimResponse response = new PushClaimResponse();
        assertThat(response.isCorrespondanceAddressUpdateSuccess(), is(true));
        assertThat(response.isPostalAddressUpdateSuccess(), is(true));
        assertThat(response.isNameUpdateSuccess(), is(true));
        assertThat(response.isContactUpdateSuccess(), is(true));
    }


    @Test
    public void setCorrespondanceAddressUpdateSuccess() {
        final PushClaimResponse response = new PushClaimResponse();
        response.setCorrespondanceAddressUpdateSuccess(CORRESPONDANCE_ADDRESS_UPDATE_FAILURE);
        assertThat(response.isCorrespondanceAddressUpdateSuccess(), is(CORRESPONDANCE_ADDRESS_UPDATE_FAILURE));
    }

    @Test
    public void setPostalAddressUpdateSuccess() {
        final PushClaimResponse response = new PushClaimResponse();
        response.setPostalAddressUpdateSuccess(POSTAL_ADDRESS_UPDATE_FAILURE);
        assertThat(response.isPostalAddressUpdateSuccess(), is(POSTAL_ADDRESS_UPDATE_FAILURE));
    }

    @Test
    public void setNameUpdateSuccess() {
        final PushClaimResponse response = new PushClaimResponse();
        response.setNameUpdateSuccess(NAME_UPDATE_FAILURE);
        assertThat(response.isNameUpdateSuccess(), is(NAME_UPDATE_FAILURE));
    }

    @Test
    public void setContactUpdateSuccess() {
        final PushClaimResponse response = new PushClaimResponse();
        response.setContactUpdateSuccess(CONTACT_UPDATE_FAILURE);
        assertThat(response.isContactUpdateSuccess(), is(CONTACT_UPDATE_FAILURE));
    }
}
