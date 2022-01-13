package uk.gov.dwp.jsa.adaptors.dto.validation;

public class PushResponse {

    private boolean isCorrespondanceAddressUpdateSuccess = true;

    private boolean isResidentialAddressUpdateSuccess = true;

    private boolean isNameUpdateSuccess = true;

    private boolean isContactUpdateSuccess = true;

    public boolean isCorrespondanceAddressUpdateSuccess() {
        return isCorrespondanceAddressUpdateSuccess;
    }

    public void setCorrespondanceAddressUpdateSuccess(final boolean correspondanceAddressUpdateSuccess) {
        this.isCorrespondanceAddressUpdateSuccess = correspondanceAddressUpdateSuccess;
    }

    public boolean isResidentialAddressUpdateSuccess() {
        return isResidentialAddressUpdateSuccess;
    }

    public void setResidentialAddressUpdateSuccess(final boolean postalAddressUpdateSuccess) {
        this.isResidentialAddressUpdateSuccess = postalAddressUpdateSuccess;
    }

    public boolean isNameUpdateSuccess() {
        return isNameUpdateSuccess;
    }

    public void setNameUpdateSuccess(final boolean nameUpdateSuccess) {
        this.isNameUpdateSuccess = nameUpdateSuccess;
    }

    public boolean isContactUpdateSuccess() {
        return isContactUpdateSuccess;
    }

    public void setContactUpdateSuccess(final boolean contactUpdateSuccess) {
        this.isContactUpdateSuccess = contactUpdateSuccess;
    }

    public boolean isSuccessful() {
        return isResidentialAddressUpdateSuccess
                && isCorrespondanceAddressUpdateSuccess
                && isNameUpdateSuccess
                && isContactUpdateSuccess;
    }
}
