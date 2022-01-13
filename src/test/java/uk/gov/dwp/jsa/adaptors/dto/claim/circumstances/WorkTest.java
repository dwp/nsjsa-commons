package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Test;
import uk.gov.dwp.jsa.adaptors.dto.claim.Address;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class WorkTest {

    public static final String EMPLOYER_NAME = "EMPLOYER_NAME";
    public static final boolean SELF_EMPLOYED_OR_DIRECTOR = true;
    public static final String EMPLOYER_PHONE_NUMBER = "EMPLOYER_PHONE_NUMBER";
    public static final Address EMPLOYER_ADDRESS = new Address();

    @Test
    public void hasDefaultFieldValues() {
        final Work work = new Work();
        assertThat(work.getEmployerName(), is(nullValue()));
        assertThat(work.getSelfEmployedOrDirector(), is(nullValue()));
        assertThat(work.getEmployerPhoneNumber(), is(nullValue()));
        assertThat(work.getEmployerAddress(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final Work work = new Work(
                EMPLOYER_NAME,
                SELF_EMPLOYED_OR_DIRECTOR,
                EMPLOYER_PHONE_NUMBER,
                EMPLOYER_ADDRESS
        );
        assertThat(work.getEmployerName(), is(EMPLOYER_NAME));
        assertThat(work.getSelfEmployedOrDirector(), is(SELF_EMPLOYED_OR_DIRECTOR));
        assertThat(work.getEmployerPhoneNumber(), is(EMPLOYER_PHONE_NUMBER));
        assertThat(work.getEmployerAddress(), is(EMPLOYER_ADDRESS));

    }

    @Test
    public void setEmployerName() {
        final Work work = new Work();
        work.setEmployerName(EMPLOYER_NAME);
        assertThat(work.getEmployerName(), is(EMPLOYER_NAME));
    }

    @Test
    public void setSelfEmployedOrDirector() {
        final Work work = new Work();
        work.setSelfEmployedOrDirector(SELF_EMPLOYED_OR_DIRECTOR);
        assertThat(work.getSelfEmployedOrDirector(), is(SELF_EMPLOYED_OR_DIRECTOR));
    }

    @Test
    public void setEmployerPhoneNumber() {
        final Work work = new Work();
        work.setEmployerPhoneNumber(EMPLOYER_PHONE_NUMBER);
        assertThat(work.getEmployerPhoneNumber(), is(EMPLOYER_PHONE_NUMBER));
    }

    @Test
    public void setEmployerAddress() {
        final Work work = new Work();
        work.setEmployerAddress(EMPLOYER_ADDRESS);
        assertThat(work.getEmployerAddress(), is(EMPLOYER_ADDRESS));
    }
}
