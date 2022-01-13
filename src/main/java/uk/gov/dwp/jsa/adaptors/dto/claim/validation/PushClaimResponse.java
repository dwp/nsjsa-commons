package uk.gov.dwp.jsa.adaptors.dto.claim.validation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PushClaimResponse {
    private boolean correspondanceAddressUpdateSuccess = true;
    private boolean postalAddressUpdateSuccess = true;
    private boolean nameUpdateSuccess = true;
    private boolean contactUpdateSuccess = true;

    public boolean isSuccessful() {
        return correspondanceAddressUpdateSuccess
                && postalAddressUpdateSuccess
                && nameUpdateSuccess
                && contactUpdateSuccess;
    }

    public boolean isCorrespondanceAddressUpdateSuccess() {
        return correspondanceAddressUpdateSuccess;
    }

    public void setCorrespondanceAddressUpdateSuccess(final boolean correspondanceAddressUpdateSuccess) {
        this.correspondanceAddressUpdateSuccess = correspondanceAddressUpdateSuccess;
    }

    public boolean isPostalAddressUpdateSuccess() {
        return postalAddressUpdateSuccess;
    }

    public void setPostalAddressUpdateSuccess(final boolean postalAddressUpdateSuccess) {
        this.postalAddressUpdateSuccess = postalAddressUpdateSuccess;
    }

    public boolean isNameUpdateSuccess() {
        return nameUpdateSuccess;
    }

    public void setNameUpdateSuccess(final boolean nameUpdateSuccess) {
        this.nameUpdateSuccess = nameUpdateSuccess;
    }

    public boolean isContactUpdateSuccess() {
        return contactUpdateSuccess;
    }

    public void setContactUpdateSuccess(final boolean contactUpdateSuccess) {
        this.contactUpdateSuccess = contactUpdateSuccess;
    }

    @Override
    public boolean equals(final Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
