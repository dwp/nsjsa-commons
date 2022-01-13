package uk.gov.dwp.jsa.adaptors.dto.jsaps;

import org.junit.Test;
import uk.gov.dwp.jsa.adaptors.dto.claim.BankDetails;
import uk.gov.dwp.jsa.adaptors.dto.claim.Claimant;
import uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.Circumstances;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class JsapsRequestTest {

    public static final boolean RECEIVING_UNIVERSAL_CREDIT = true;
    public static final String ESJ_NUMBER = "ESJ_NUMBER";
    public static final String HOME_OFFICE_ID = "HOME_OFFICE_ID";
    public static final String TARGET_OFFICE_ID = "TARGET_OFFICE_ID";
    public static final Circumstances CIRCUMSTANCES = new Circumstances();
    public static final Claimant CLAIMANT = new Claimant();
    public static final boolean AGENT_MODE = true;
    public static final BankDetails BANK_DETAILS = new BankDetails();

    @Test
    public void hasDefaultFieldValues() {
        final JsapsRequest jsapsRequest = new JsapsRequest();
        assertThat(jsapsRequest.getReceivingUniversalCredit(), is(nullValue()));
        assertThat(jsapsRequest.getEsjNumber(), is(nullValue()));
        assertThat(jsapsRequest.getHomeOfficeId(), is(nullValue()));
        assertThat(jsapsRequest.getTargetOfficeId(), is(nullValue()));
        assertThat(jsapsRequest.getCircumstances(), is(nullValue()));
        assertThat(jsapsRequest.getClaimant(), is(nullValue()));
        assertThat(jsapsRequest.getAgentMode(), is(nullValue()));
        assertThat(jsapsRequest.getBankDetails(), is(nullValue()));
    }


    @Test
    public void setReceivingUniversalCredit() {
        final JsapsRequest jsapsRequest = new JsapsRequest();
        jsapsRequest.setReceivingUniversalCredit(RECEIVING_UNIVERSAL_CREDIT);
        assertThat(jsapsRequest.getReceivingUniversalCredit(), is(RECEIVING_UNIVERSAL_CREDIT));
    }

    @Test
    public void setEsjNumber() {
        final JsapsRequest jsapsRequest = new JsapsRequest();
        jsapsRequest.setEsjNumber(ESJ_NUMBER);
        assertThat(jsapsRequest.getEsjNumber(), is(ESJ_NUMBER));
    }

    @Test
    public void setHomeOfficeId() {
        final JsapsRequest jsapsRequest = new JsapsRequest();
        jsapsRequest.setHomeOfficeId(HOME_OFFICE_ID);
        assertThat(jsapsRequest.getHomeOfficeId(), is(HOME_OFFICE_ID));
    }

    @Test
    public void setTargetOfficeId() {
        final JsapsRequest jsapsRequest = new JsapsRequest();
        jsapsRequest.setTargetOfficeId(TARGET_OFFICE_ID);
        assertThat(jsapsRequest.getTargetOfficeId(), is(TARGET_OFFICE_ID));
    }

    @Test
    public void setCircumstances() {
        final JsapsRequest jsapsRequest = new JsapsRequest();
        jsapsRequest.setCircumstances(CIRCUMSTANCES);
        assertThat(jsapsRequest.getCircumstances(), is(CIRCUMSTANCES));
    }

    @Test
    public void setClaimant() {
        final JsapsRequest jsapsRequest = new JsapsRequest();
        jsapsRequest.setClaimant(CLAIMANT);
        assertThat(jsapsRequest.getClaimant(), is(CLAIMANT));
    }

    @Test
    public void setAgentMode() {
        final JsapsRequest jsapsRequest = new JsapsRequest();
        jsapsRequest.setAgentMode(AGENT_MODE);
        assertThat(jsapsRequest.getAgentMode(), is(AGENT_MODE));
    }

    @Test
    public void setBankDetails() {
        final JsapsRequest jsapsRequest = new JsapsRequest();
        jsapsRequest.setBankDetails(BANK_DETAILS);
        assertThat(jsapsRequest.getBankDetails(), is(BANK_DETAILS));
    }
}
