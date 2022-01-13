package uk.gov.dwp.jsa.adaptors.dto.claim;

import org.junit.Test;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.CurrentStatus;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ClaimantTest {

    public static final LanguagePreference LANGUAGE_PREFERENCE = new LanguagePreference();
    public static final CurrentStatus CURRENT_STATUS = new CurrentStatus();
    public static final String NINO = "NINO";
    public static final UUID CLAIMANT_ID = UUID.randomUUID();
    public static final Name NAME = new Name();
    public static final LocalDate DATE_OF_BIRTH = LocalDate.now();
    public static final Address ADDRESS = new Address();
    public static final Address POSTAL_ADDRESS = new Address();
    public static final ContactDetails CONTACT_DETAILS = new ContactDetails();
    public static final String SERVICE_VERSION = "SERVICE_VERSION";
    public static final String EMAIL = "EMAIL";

    @Test
    public void hasDefaultFieldValues() {
        final Claimant claimant = new Claimant();
        assertThat(claimant.getLanguagePreference(), is(nullValue()));
        assertThat(claimant.getCurrentStatus(), is(nullValue()));
        assertThat(claimant.getNino(), is(nullValue()));
        assertThat(claimant.getClaimantId(), is(nullValue()));
        assertThat(claimant.getName(), is(nullValue()));
        assertThat(claimant.getDateOfBirth(), is(nullValue()));
        assertThat(claimant.getAddress(), is(nullValue()));
        assertThat(claimant.getPostalAddress(), is(nullValue()));
        assertThat(claimant.getContactDetails(), is(nullValue()));
        assertThat(claimant.getServiceVersion(), is(nullValue()));
    }

    @Test
    public void setLanguagePreference() {
        final Claimant claimant = new Claimant();
        claimant.setLanguagePreference(LANGUAGE_PREFERENCE);
        assertThat(claimant.getLanguagePreference(), is(LANGUAGE_PREFERENCE));
    }

    @Test
    public void setCurrentStatus() {
        final Claimant claimant = new Claimant();
        claimant.setCurrentStatus(CURRENT_STATUS);
        assertThat(claimant.getCurrentStatus(), is(CURRENT_STATUS));
    }

    @Test
    public void setNino() {
        final Claimant claimant = new Claimant();
        claimant.setNino(NINO);
        assertThat(claimant.getNino(), is(NINO));
    }

    @Test
    public void setClaimantId() {
        final Claimant claimant = new Claimant();
        claimant.setClaimantId(CLAIMANT_ID);
        assertThat(claimant.getClaimantId(), is(CLAIMANT_ID));
    }

    @Test
    public void setName() {
        final Claimant claimant = new Claimant();
        claimant.setName(NAME);
        assertThat(claimant.getName(), is(NAME));
    }

    @Test
    public void setDateOfBirth() {
        final Claimant claimant = new Claimant();
        claimant.setDateOfBirth(DATE_OF_BIRTH);
        assertThat(claimant.getDateOfBirth(), is(DATE_OF_BIRTH));
    }

    @Test
    public void setAddress() {
        final Claimant claimant = new Claimant();
        claimant.setAddress(ADDRESS);
        assertThat(claimant.getAddress(), is(ADDRESS));
    }

    @Test
    public void setPostalAddress() {
        final Claimant claimant = new Claimant();
        claimant.setPostalAddress(POSTAL_ADDRESS);
        assertThat(claimant.getPostalAddress(), is(POSTAL_ADDRESS));
    }

    @Test
    public void setContactDetails() {
        final Claimant claimant = new Claimant();
        claimant.setContactDetails(CONTACT_DETAILS);
        assertThat(claimant.getContactDetails(), is(CONTACT_DETAILS));
    }

    @Test
    public void setServiceVersion() {
        final Claimant claimant = new Claimant();
        claimant.setServiceVersion(SERVICE_VERSION);
        assertThat(claimant.getServiceVersion(), is(SERVICE_VERSION));
    }

    @Test
    public void hasEmailAddressReturnsTrue() {
        final Claimant claimant = new Claimant();
        final ContactDetails contactDetails = new ContactDetails();
        contactDetails.setEmail(EMAIL);
        claimant.setContactDetails(contactDetails);
        assertThat(claimant.hasEmailAddress(), is(true));
    }

    @Test
    public void hasSuccessNotificationReturnsTrue() {
        final Claimant claimant = new Claimant();
        claimant.setSuccessNotification(Boolean.TRUE);
        assertThat(claimant.getSuccessNotification(), is(true));
    }

    @Test
    public void hasProgressNotificationReturnsTrue() {
        final Claimant claimant = new Claimant();
        claimant.setProgressNotification(Boolean.TRUE);
        assertThat(claimant.getProgressNotification(), is(true));
    }

    @Test
    public void hasEmailAddressReturnsFalse() {
        final Claimant claimant = new Claimant();
        assertThat(claimant.hasEmailAddress(), is(false));
    }

    @Test
    public void hasAnotherPostalAddressReturmnsTrue() {
        final Claimant claimant = new Claimant();
        claimant.setPostalAddress(POSTAL_ADDRESS);
        assertThat(claimant.hasAnotherPostalAddress(), is(true));
    }

    @Test
    public void hasAnotherPostalAddressReturmnsFalse() {
        final Claimant claimant = new Claimant();
        assertThat(claimant.hasAnotherPostalAddress(), is(false));
    }
}
