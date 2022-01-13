package uk.gov.dwp.jsa.adaptors.dto.claim;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class AddressTest {

    public static final String FIRST_LINE = "FIRST_LINE";
    public static final String SECOND_LINE = "SECOND_LINE";
    public static final String POSTCODE = "POSTCODE";
    public static final String TOWN = "TOWN";
    public static final String COUNTRY = "COUNTRY";

    @Test
    public void hasDefaultFieldValues() {
        final Address address = new Address();
        assertThat(address.getFirstLine(), is(nullValue()));
        assertThat(address.getSecondLine(), is(nullValue()));
        assertThat(address.getPostCode(), is(nullValue()));
        assertThat(address.getTown(), is(nullValue()));
        assertThat(address.getCountry(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final Address address = new Address(FIRST_LINE, SECOND_LINE, POSTCODE, TOWN, COUNTRY);
        assertThat(address.getFirstLine(), is(FIRST_LINE));
        assertThat(address.getSecondLine(), is(SECOND_LINE));
        assertThat(address.getPostCode(), is(POSTCODE));
        assertThat(address.getTown(), is(TOWN));
        assertThat(address.getCountry(), is(COUNTRY));

    }

    @Test
    public void setFirstLine() {
        final Address address = new Address();
        address.setFirstLine(FIRST_LINE);
        assertThat(address.getFirstLine(), is(FIRST_LINE));
    }

    @Test
    public void setSecondLine() {
        final Address address = new Address();
        address.setSecondLine(SECOND_LINE);
        assertThat(address.getSecondLine(), is(SECOND_LINE));
    }

    @Test
    public void setPostCode() {
        final Address address = new Address();
        address.setPostCode(POSTCODE);
        assertThat(address.getPostCode(), is(POSTCODE));
    }

    @Test
    public void setTown() {
        final Address address = new Address();
        address.setTown(TOWN);
        assertThat(address.getTown(), is(TOWN));
    }

    @Test
    public void setCountry() {
        final Address address = new Address();
        address.setCountry(COUNTRY);
        assertThat(address.getCountry(), is(COUNTRY));
    }
}
