package uk.gov.dwp.jsa.adaptors.dto.claim;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.lang3.StringUtils;
import uk.gov.dwp.jsa.adaptors.ObjectUtils;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.CurrentStatus;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Claimant {
    //Request
    private UUID claimantId;
    private String serviceVersion;

    //Request & Response
    private String nino;
    private Name name;
    private LocalDate dateOfBirth;
    private LocalDate dateOfClaim;
    @SuppressFBWarnings("UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR")
    private ContactDetails contactDetails;
    private Address address;
    private Address postalAddress;
    private CurrentStatus currentStatus;
    private LanguagePreference languagePreference;
    private Boolean successNotification;
    private Boolean progressNotification;

    public Claimant() {
    }

    public LanguagePreference getLanguagePreference() {
        return languagePreference;
    }

    public void setLanguagePreference(final LanguagePreference languagePreference) {
        this.languagePreference = languagePreference;
    }

    public CurrentStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(final CurrentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void setDateOfClaim(final LocalDate dateOfClaim) {
        this.dateOfClaim = dateOfClaim;
    }

    public LocalDate getDateOfClaim() {
        return dateOfClaim;
    }

    public String getNino() {
        return nino;
    }

    public void setNino(final String nino) {
        this.nino = nino;
    }

    public UUID getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(final UUID claimantId) {
        this.claimantId = claimantId;
    }

    public Name getName() {
        return name;
    }

    public void setName(final Name name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(final Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(final ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(final String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public boolean hasEmailAddress() {
        return ObjectUtils.resolve(() -> contactDetails.getEmail()).filter(StringUtils::isNotBlank).isPresent();
    }

    public boolean hasAnotherPostalAddress() {
        return !Objects.isNull(postalAddress);
    }

    public Boolean getSuccessNotification() {
        return successNotification;
    }

    public void setSuccessNotification(final Boolean successNotification) {
        this.successNotification = successNotification;
    }

    public Boolean getProgressNotification() {
        return progressNotification;
    }

    public void setProgressNotification(final Boolean progressNotification) {
        this.progressNotification = progressNotification;
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
