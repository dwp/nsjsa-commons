package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Test;
import uk.gov.dwp.jsa.adaptors.dto.claim.Address;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class PensionDetailTest {

    public static final LocalDate START_DATE = LocalDate.now();
    public static final String PROVIDER_NAME = "PROVIDER_NAME";
    public static final BigDecimal GROSS_PAY = BigDecimal.ONE;
    public static final String PAYMENT_FREQUENCY = "WEEKLY";
    public static final boolean HAS_PERIODIC_INCREASE = true;
    public static final String PENSION_INCREASE_MONTH = "JULY";
    public static final Address PROVIDER_ADDRESS = new Address();

    @Test
    public void hasDefaultFieldValues() {
        final PensionDetail pensionDetail = new PensionDetail();
        assertThat(pensionDetail.getStartDate(), is(nullValue()));
        assertThat(pensionDetail.getProviderName(), is(nullValue()));
        assertThat(pensionDetail.getGrossPay(), is(nullValue()));
        assertThat(pensionDetail.getPaymentFrequency(), is(nullValue()));
        assertThat(pensionDetail.getHasPeriodicIncrease(), is(nullValue()));
        assertThat(pensionDetail.getPensionIncreaseMonth(), is(nullValue()));
        assertThat(pensionDetail.getProviderAddress(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final PensionDetail pensionDetail = new PensionDetail(
                START_DATE, PROVIDER_NAME, GROSS_PAY, PAYMENT_FREQUENCY, HAS_PERIODIC_INCREASE,
                PENSION_INCREASE_MONTH, PROVIDER_ADDRESS);
        assertThat(pensionDetail.getStartDate(), is(START_DATE));
        assertThat(pensionDetail.getProviderName(), is(PROVIDER_NAME));
        assertThat(pensionDetail.getGrossPay(), is(GROSS_PAY));
        assertThat(pensionDetail.getPaymentFrequency(), is(PAYMENT_FREQUENCY));
        assertThat(pensionDetail.getHasPeriodicIncrease(), is(HAS_PERIODIC_INCREASE));
        assertThat(pensionDetail.getPensionIncreaseMonth(), is(PENSION_INCREASE_MONTH));
        assertThat(pensionDetail.getProviderAddress(), is(PROVIDER_ADDRESS));

    }

    @Test
    public void setStartDate() {
        final PensionDetail pensionDetail = new PensionDetail();
        pensionDetail.setStartDate(START_DATE);
        assertThat(pensionDetail.getStartDate(), is(START_DATE));
    }

    @Test
    public void setProviderName() {
        final PensionDetail pensionDetail = new PensionDetail();
        pensionDetail.setProviderName(PROVIDER_NAME);
        assertThat(pensionDetail.getProviderName(), is(PROVIDER_NAME));
    }

    @Test
    public void setGrossPay() {
        final PensionDetail pensionDetail = new PensionDetail();
        pensionDetail.setGrossPay(GROSS_PAY);
        assertThat(pensionDetail.getGrossPay(), is(GROSS_PAY));
    }

    @Test
    public void setPaymentFrequency() {
        final PensionDetail pensionDetail = new PensionDetail();
        pensionDetail.setPaymentFrequency(PAYMENT_FREQUENCY);
        assertThat(pensionDetail.getPaymentFrequency(), is(PAYMENT_FREQUENCY));
    }

    @Test
    public void setHasPeriodicIncrease() {
        final PensionDetail pensionDetail = new PensionDetail();
        pensionDetail.setHasPeriodicIncrease(HAS_PERIODIC_INCREASE);
        assertThat(pensionDetail.getHasPeriodicIncrease(), is(HAS_PERIODIC_INCREASE));
    }

    @Test
    public void setPensionIncreaseMonth() {
        final PensionDetail pensionDetail = new PensionDetail();
        pensionDetail.setPensionIncreaseMonth(PENSION_INCREASE_MONTH);
        assertThat(pensionDetail.getPensionIncreaseMonth(), is(PENSION_INCREASE_MONTH));
    }

    @Test
    public void setProviderAddress() {
        final PensionDetail pensionDetail = new PensionDetail();
        pensionDetail.setProviderAddress(PROVIDER_ADDRESS);
        assertThat(pensionDetail.getProviderAddress(), is(PROVIDER_ADDRESS));
    }
}
