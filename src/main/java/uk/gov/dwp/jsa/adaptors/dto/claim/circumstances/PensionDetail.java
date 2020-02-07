package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;


import uk.gov.dwp.jsa.adaptors.dto.claim.Address;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class PensionDetail {

    private LocalDate startDate;

    private String providerName;

    private BigDecimal grossPay;

    private String paymentFrequency;

    private Boolean hasPeriodicIncrease;

    private String pensionIncreaseMonth;

    private Address providerAddress;

    public PensionDetail() {
    }

    public PensionDetail(
            final LocalDate startDate,
            final String providerName,
            final BigDecimal grossPay,
            final String paymentFrequency,
            final Boolean hasPeriodicIncrease,
            final String pensionIncreaseMonth,
            final Address providerAddress) {
        this.startDate = startDate;
        this.providerName = providerName;
        this.grossPay = grossPay;
        this.paymentFrequency = paymentFrequency;
        this.hasPeriodicIncrease = hasPeriodicIncrease;
        this.pensionIncreaseMonth = pensionIncreaseMonth;
        this.providerAddress = providerAddress;
    }

    public Boolean getHasPeriodicIncrease() {
        return hasPeriodicIncrease;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(final String providerName) {
        this.providerName = providerName;
    }

    public BigDecimal getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(final BigDecimal grossPay) {
        this.grossPay = grossPay;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(final String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public Boolean isHasPeriodicIncrease() {
        return hasPeriodicIncrease;
    }

    public void setHasPeriodicIncrease(final Boolean hasPeriodicIncrease) {
        this.hasPeriodicIncrease = hasPeriodicIncrease;
    }

    public String getPensionIncreaseMonth() {
        return pensionIncreaseMonth;
    }

    public void setPensionIncreaseMonth(final String pensionIncreaseMonth) {
        this.pensionIncreaseMonth = pensionIncreaseMonth;
    }

    public Address getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(final Address providerAddress) {
        this.providerAddress = providerAddress;
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
