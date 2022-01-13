package uk.gov.dwp.jsa.adaptors.dto.claim;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class BankDetailsTest {

    public static final UUID CLAIMANT_ID = UUID.randomUUID();
    public static final String ACCOUNT_HOLDER = "ACCOUNT_HOLDER";
    public static final String SORT_CODE = "SORT_CODE";
    public static final String ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
    public static final String REFERENCE = "REFERENCE";
    public static final String SERVICE_VERSION = "SERVICE_VERSION";
    public static final UUID ID = UUID.randomUUID();

    @Test
    public void hasDefaultFieldValues() {
        final BankDetails bankDetails = new BankDetails();
        assertThat(bankDetails.getClaimantId(), is(nullValue()));
        assertThat(bankDetails.getAccountHolder(), is(nullValue()));
        assertThat(bankDetails.getSortCode(), is(nullValue()));
        assertThat(bankDetails.getAccountNumber(), is(nullValue()));
        assertThat(bankDetails.getReference(), is(nullValue()));
        assertThat(bankDetails.getServiceVersion(), is(nullValue()));
        assertThat(bankDetails.getId(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final BankDetails bankDetails = new BankDetails(ID, CLAIMANT_ID, ACCOUNT_HOLDER, SORT_CODE, ACCOUNT_NUMBER, REFERENCE);
        assertThat(bankDetails.getClaimantId(), is(CLAIMANT_ID));
        assertThat(bankDetails.getAccountHolder(), is(ACCOUNT_HOLDER));
        assertThat(bankDetails.getSortCode(), is(SORT_CODE));
        assertThat(bankDetails.getAccountNumber(), is(ACCOUNT_NUMBER));
        assertThat(bankDetails.getReference(), is(REFERENCE));
        assertThat(bankDetails.getServiceVersion(), is(nullValue()));
        assertThat(bankDetails.getId(), is(ID));

    }

    @Test
    public void setClaimantId() {
        final BankDetails bankDetails = new BankDetails();
        bankDetails.setClaimantId(CLAIMANT_ID);
        assertThat(bankDetails.getClaimantId(), is(CLAIMANT_ID));
    }

    @Test
    public void setAccountHolder() {
        final BankDetails bankDetails = new BankDetails();
        bankDetails.setAccountHolder(ACCOUNT_HOLDER);
        assertThat(bankDetails.getAccountHolder(), is(ACCOUNT_HOLDER));
    }

    @Test
    public void setSortCode() {
        final BankDetails bankDetails = new BankDetails();
        bankDetails.setSortCode(SORT_CODE);
        assertThat(bankDetails.getSortCode(), is(SORT_CODE));
    }

    @Test
    public void setAccountNumber() {
        final BankDetails bankDetails = new BankDetails();
        bankDetails.setAccountNumber(ACCOUNT_NUMBER);
        assertThat(bankDetails.getAccountNumber(), is(ACCOUNT_NUMBER));
    }

    @Test
    public void setReference() {
        final BankDetails bankDetails = new BankDetails();
        bankDetails.setReference(REFERENCE);
        assertThat(bankDetails.getReference(), is(REFERENCE));
    }

    @Test
    public void setServiceVersion() {
        final BankDetails bankDetails = new BankDetails();
        bankDetails.setServiceVersion(SERVICE_VERSION);
        assertThat(bankDetails.getServiceVersion(), is(SERVICE_VERSION));
    }

    @Test
    public void setId() {
        final BankDetails bankDetails = new BankDetails();
        bankDetails.setId(ID);
        assertThat(bankDetails.getId(), is(ID));
    }

}
