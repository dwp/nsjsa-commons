package uk.gov.dwp.jsa.adaptors.dto.validation;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PushResponseTest {

    public static final boolean CONTACT_UPDATE_FAILURE = false;
    public static final boolean RESIDENTIAL_ADDRESS_UPDATE_FAILURE = false;
    public static final boolean NAME_UPDATE_FAILURE = false;
    public static final boolean CORRESPONDANCE_ADDRESS_UPDATE_FAILURE = false;

    @Test
    public void isSuccessfulReturnsTrue() {
        final PushResponse response = new PushResponse();
        assertThat(response.isSuccessful(), is(true));

    }

    @Test
    public void hasDefaultFieldValues() {
        final PushResponse response = new PushResponse();
        assertThat(response.isCorrespondanceAddressUpdateSuccess(), is(true));
        assertThat(response.isResidentialAddressUpdateSuccess(), is(true));
        assertThat(response.isNameUpdateSuccess(), is(true));
        assertThat(response.isContactUpdateSuccess(), is(true));
    }


    @Test
    public void setCorrespondanceAddressUpdateSuccess() {
        final PushResponse response = new PushResponse();
        response.setCorrespondanceAddressUpdateSuccess(CORRESPONDANCE_ADDRESS_UPDATE_FAILURE);
        assertThat(response.isCorrespondanceAddressUpdateSuccess(), is(CORRESPONDANCE_ADDRESS_UPDATE_FAILURE));
    }

    @Test
    public void setPostalAddressUpdateSuccess() {
        final PushResponse response = new PushResponse();
        response.setResidentialAddressUpdateSuccess(RESIDENTIAL_ADDRESS_UPDATE_FAILURE);
        assertThat(response.isResidentialAddressUpdateSuccess(), is(RESIDENTIAL_ADDRESS_UPDATE_FAILURE));
    }

    @Test
    public void setNameUpdateSuccess() {
        final PushResponse response = new PushResponse();
        response.setNameUpdateSuccess(NAME_UPDATE_FAILURE);
        assertThat(response.isNameUpdateSuccess(), is(NAME_UPDATE_FAILURE));
    }

    @Test
    public void setContactUpdateSuccess() {
        final PushResponse response = new PushResponse();
        response.setContactUpdateSuccess(CONTACT_UPDATE_FAILURE);
        assertThat(response.isContactUpdateSuccess(), is(CONTACT_UPDATE_FAILURE));
    }

}
