package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import uk.gov.dwp.jsa.adaptors.dto.claim.Address;

import java.time.LocalDate;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class PreviousWork extends Work {


    private LocalDate startDate;

    private LocalDate endDate;

    private String reasonEnded;

    private String otherReasonDetails;

    private Boolean paymentExpected;

    public PreviousWork() {
    }

    public PreviousWork(final String employerName,
                        final Boolean isSelfEmployedOrDirector,
                        final String employerPhoneNumber,
                        final Address employerAddress,
                        final LocalDate startDate,
                        final LocalDate endDate,
                        final String reasonEnded,
                        final String otherReasonDetails,
                        final Boolean paymentExpected) {
        super(employerName, isSelfEmployedOrDirector, employerPhoneNumber, employerAddress);
        this.startDate = startDate;
        this.endDate = endDate;
        this.reasonEnded = reasonEnded;
        this.otherReasonDetails = otherReasonDetails;
        this.paymentExpected = paymentExpected;
    }

    public Boolean getPaymentExpected() {
        return paymentExpected;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReasonEnded() {
        return reasonEnded;
    }

    public void setReasonEnded(final String reasonEnded) {
        this.reasonEnded = reasonEnded;
    }

    public String getOtherReasonDetails() {
        return otherReasonDetails;
    }

    public void setOtherReasonDetails(final String otherReasonDetails) {
        this.otherReasonDetails = otherReasonDetails;
    }

    public Boolean isPaymentExpected() {
        return paymentExpected;
    }

    public void setPaymentExpected(final Boolean paymentExpected) {
        this.paymentExpected = paymentExpected;
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
