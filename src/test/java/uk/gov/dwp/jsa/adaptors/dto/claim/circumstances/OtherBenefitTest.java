package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class OtherBenefitTest {

    public static final String DESCRIPTION = "DESCRIPTION";

    @Test
    public void hasDefaultFieldValues() {
        final OtherBenefit otherBenefit = new OtherBenefit();
        assertThat(otherBenefit.getDescription(), is(nullValue()));

    }

    @Test
    public void constuctorSetsFieldValues() {
        final OtherBenefit otherBenefit = new OtherBenefit(DESCRIPTION);
        assertThat(otherBenefit.getDescription(), is(DESCRIPTION));
    }

    @Test
    public void setDescription() {
        final OtherBenefit otherBenefit = new OtherBenefit();
        otherBenefit.setDescription(DESCRIPTION);
        assertThat(otherBenefit.getDescription(), is(DESCRIPTION));
    }
}
