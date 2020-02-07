package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Test;
import uk.gov.dwp.jsa.adaptors.dto.claim.Address;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class CurrentWorkTest {

    public static final boolean VOLUNTARY = true;
    public static final boolean PAID = true;
    public static final BigDecimal NET_PAY = BigDecimal.ONE;
    public static final String PAYMENT_FREQUENCY = "WEEKLY";
    public static final boolean CAN_CHOOSE_IF_PAID = true;
    public static final boolean VOLUNTARY_JOB_PAID = true;
    public static final int HOURS_PER_WEEK = 1;
    public static final String EMPLOYER_NAME = "EMPLOYER_NAME";
    public static final boolean IS_SELF_EMPLOYED_OR_DIRECTOR = true;
    public static final String EMPLOYER_PHONE_NUMBER = "EMPLOYER_PHONE_NUMBER";
    public static final Address EMPLOYER_ADDRESS = new Address();

    @Test
    public void constructorSetsFieldValues() {
        final CurrentWork currentWork = new CurrentWork(
                EMPLOYER_NAME,
                IS_SELF_EMPLOYED_OR_DIRECTOR,
                EMPLOYER_PHONE_NUMBER,
                EMPLOYER_ADDRESS,
                VOLUNTARY,
                PAID,
                NET_PAY,
                PAYMENT_FREQUENCY,
                CAN_CHOOSE_IF_PAID,
                VOLUNTARY_JOB_PAID,
                HOURS_PER_WEEK);

        assertThat(currentWork.getEmployerName(), is(EMPLOYER_NAME));
        assertThat(currentWork.getEmployerPhoneNumber(), is(EMPLOYER_PHONE_NUMBER));
        assertThat(currentWork.getEmployerAddress(), is(EMPLOYER_ADDRESS));
        assertThat(currentWork.isSelfEmployedOrDirector(), is(IS_SELF_EMPLOYED_OR_DIRECTOR));
        assertThat(currentWork.isVoluntary(), is(VOLUNTARY));
        assertThat(currentWork.getVoluntary(), is(VOLUNTARY));
        assertThat(currentWork.isPaid(), is(PAID));
        assertThat(currentWork.getNetPay(), is(NET_PAY));
        assertThat(currentWork.getPaymentFrequency(), is(PAYMENT_FREQUENCY));
        assertThat(currentWork.isCanChooseIfPaid(), is(CAN_CHOOSE_IF_PAID));
        assertThat(currentWork.isVoluntaryJobPaid(), is(VOLUNTARY_JOB_PAID));
        assertThat(currentWork.getHoursPerWeek(), is(HOURS_PER_WEEK));
    }

    @Test
    public void hasDefaultFieldValues() {
        final CurrentWork currentWork = new CurrentWork();
        assertThat(currentWork.getEmployerName(), is(nullValue()));
        assertThat(currentWork.getEmployerPhoneNumber(), is(nullValue()));
        assertThat(currentWork.getEmployerAddress(), is(nullValue()));
        assertThat(currentWork.isSelfEmployedOrDirector(), is(nullValue()));
        assertThat(currentWork.isVoluntary(), is(nullValue()));
        assertThat(currentWork.getVoluntary(), is(nullValue()));
        assertThat(currentWork.isPaid(), is(nullValue()));
        assertThat(currentWork.getNetPay(), is(nullValue()));
        assertThat(currentWork.getPaymentFrequency(), is(nullValue()));
        assertThat(currentWork.isCanChooseIfPaid(), is(nullValue()));
        assertThat(currentWork.isVoluntaryJobPaid(), is(nullValue()));
        assertThat(currentWork.getHoursPerWeek(), is(nullValue()));
    }

    @Test
    public void setVoluntary() {
        final CurrentWork currentWork = new CurrentWork();
        currentWork.setVoluntary(VOLUNTARY);
        assertThat(currentWork.isVoluntary(), is(VOLUNTARY));
        assertThat(currentWork.getVoluntary(), is(VOLUNTARY));
    }

    @Test
    public void setPaid() {
        final CurrentWork currentWork = new CurrentWork();
        currentWork.setPaid(PAID);
        assertThat(currentWork.isPaid(), is(PAID));
    }

    @Test
    public void setNetPay() {
        final CurrentWork currentWork = new CurrentWork();
        currentWork.setNetPay(NET_PAY);
        assertThat(currentWork.getNetPay(), is(NET_PAY));
    }

    @Test
    public void setPaymentFrequency() {
        final CurrentWork currentWork = new CurrentWork();
        currentWork.setPaymentFrequency(PAYMENT_FREQUENCY);
        assertThat(currentWork.getPaymentFrequency(), is(PAYMENT_FREQUENCY));
    }

    @Test
    public void setCanChooseIfPaid() {
        final CurrentWork currentWork = new CurrentWork();
        currentWork.setCanChooseIfPaid(CAN_CHOOSE_IF_PAID);
        assertThat(currentWork.isCanChooseIfPaid(), is(CAN_CHOOSE_IF_PAID));
    }

    @Test
    public void setVoluntaryJobPaid() {
        final CurrentWork currentWork = new CurrentWork();
        currentWork.setVoluntaryJobPaid(VOLUNTARY_JOB_PAID);
        assertThat(currentWork.isVoluntaryJobPaid(), is(VOLUNTARY_JOB_PAID));
    }

    @Test
    public void setHoursPerWeek() {
        final CurrentWork currentWork = new CurrentWork();
        currentWork.setHoursPerWeek(HOURS_PER_WEEK);
        assertThat(currentWork.getHoursPerWeek(), is(HOURS_PER_WEEK));
    }
}