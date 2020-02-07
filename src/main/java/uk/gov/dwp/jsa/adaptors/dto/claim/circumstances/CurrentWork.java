package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import uk.gov.dwp.jsa.adaptors.dto.claim.Address;

import java.math.BigDecimal;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class CurrentWork extends Work {

    private Boolean isVoluntary;

    private Boolean isPaid;

    private BigDecimal netPay;

    private String paymentFrequency;

    private Boolean canChooseIfPaid;

    private Boolean isVoluntaryJobPaid;

    private Integer hoursPerWeek;

    public CurrentWork() {
    }

    public CurrentWork(
            final String employerName,
            final Boolean isSelfEmployedOrDirector,
            final String employerPhoneNumber,
            final Address employerAddress,
            final Boolean isVoluntary,
            final Boolean isPaid,
            final BigDecimal netPay,
            final String paymentFrequency,
            final Boolean canChooseIfPaid,
            final Boolean isVoluntaryJobPaid,
            final Integer hoursPerWeek) {
        super(employerName,
                isSelfEmployedOrDirector,
                employerPhoneNumber,
                employerAddress);
        this.isVoluntary = isVoluntary;
        this.isPaid = isPaid;
        this.netPay = netPay;
        this.paymentFrequency = paymentFrequency;
        this.canChooseIfPaid = canChooseIfPaid;
        this.isVoluntaryJobPaid = isVoluntaryJobPaid;
        this.hoursPerWeek = hoursPerWeek;
    }

    public Boolean getVoluntary() {
        return isVoluntary;
    }

    public Boolean isVoluntary() {
        return isVoluntary;
    }

    public void setVoluntary(final Boolean voluntary) {
        isVoluntary = voluntary;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public void setPaid(final Boolean paid) {
        isPaid = paid;
    }

    public BigDecimal getNetPay() {
        return netPay;
    }

    public void setNetPay(final BigDecimal netPay) {
        this.netPay = netPay;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(final String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public Boolean isCanChooseIfPaid() {
        return canChooseIfPaid;
    }

    public void setCanChooseIfPaid(final Boolean canChooseIfPaid) {
        this.canChooseIfPaid = canChooseIfPaid;
    }

    public Boolean isVoluntaryJobPaid() {
        return isVoluntaryJobPaid;
    }

    public void setVoluntaryJobPaid(final Boolean voluntaryJobPaid) {
        isVoluntaryJobPaid = voluntaryJobPaid;
    }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(final Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    public boolean equals(final Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
