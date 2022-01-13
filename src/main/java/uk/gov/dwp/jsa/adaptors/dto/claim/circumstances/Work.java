package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;


import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.gov.dwp.jsa.adaptors.dto.claim.Address;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Work {

    private String employerName;

    private Boolean isSelfEmployedOrDirector;

    @JsonIgnore
    private String employerPhoneNumber;

    private Address employerAddress;

    public Work() {
    }

    public Work(
            final String employerName,
            final Boolean isSelfEmployedOrDirector,
            final String employerPhoneNumber,
            final Address employerAddress) {
        this.employerName = employerName;
        this.isSelfEmployedOrDirector = isSelfEmployedOrDirector;
        this.employerPhoneNumber = employerPhoneNumber;
        this.employerAddress = employerAddress;
    }

    public Boolean getSelfEmployedOrDirector() {
        return isSelfEmployedOrDirector;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(final String employerName) {
        this.employerName = employerName;
    }

    public Boolean isSelfEmployedOrDirector() {
        return isSelfEmployedOrDirector;
    }

    public void setSelfEmployedOrDirector(final Boolean selfEmployedOrDirector) {
        isSelfEmployedOrDirector = selfEmployedOrDirector;
    }

    public String getEmployerPhoneNumber() {
        return employerPhoneNumber;
    }

    public void setEmployerPhoneNumber(final String employerPhoneNumber) {
        this.employerPhoneNumber = employerPhoneNumber;
    }

    public Address getEmployerAddress() {
        return employerAddress;
    }

    public void setEmployerAddress(final Address employerAddress) {
        this.employerAddress = employerAddress;
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
