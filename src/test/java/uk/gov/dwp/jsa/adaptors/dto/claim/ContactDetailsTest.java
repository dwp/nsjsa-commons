package uk.gov.dwp.jsa.adaptors.dto.claim;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ContactDetailsTest {

    public static final String NUMBER = "NUMBER";
    public static final String EMAIL = "EMAIL";
    private static final Boolean NUMBER_PROVIDED = true;
    private static final Boolean EMAIL_PROVIDED = true;

    @Test
    public void hasDefaultFieldValues() {
        final ContactDetails contactDetails = new ContactDetails();
        assertThat(contactDetails.getNumber(), is(nullValue()));
        assertThat(contactDetails.getEmail(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final ContactDetails contactDetails = new ContactDetails(NUMBER, EMAIL, NUMBER_PROVIDED, EMAIL_PROVIDED);
        assertThat(contactDetails.getNumber(), is(NUMBER));
        assertThat(contactDetails.getEmail(), is(EMAIL));
    }

    @Test
    public void setNumber() {
        final ContactDetails contactDetails = new ContactDetails();
        contactDetails.setNumber(NUMBER);
        assertThat(contactDetails.getNumber(), is(NUMBER));
    }

    @Test
    public void setEmail() {
        final ContactDetails contactDetails = new ContactDetails();
        contactDetails.setEmail(EMAIL);
        assertThat(contactDetails.getEmail(), is(EMAIL));
    }
}
