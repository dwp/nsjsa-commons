package uk.gov.dwp.jsa.adaptors.dto.claim;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;

import java.util.UUID;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class BankDetails {
    //Response

    private UUID id;

    private UUID claimantId;

    //Request
    private String accountHolder;

    private String sortCode;

    private String accountNumber;

    private String reference;

    private String serviceVersion;

    public BankDetails(final UUID id, final UUID claimantId, final String accountHolder, final String sortCode,
                       final String accountNumber, final String reference) {
        this.id = id;
        this.claimantId = claimantId;
        this.accountHolder = accountHolder;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
        this.reference = reference;
    }

    public BankDetails() {
    }

    public UUID getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(final UUID claimantId) {
        this.claimantId = claimantId;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(final String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(final String sortCode) {
        this.sortCode = sortCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(final String reference) {
        this.reference = reference;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(final String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return StringUtils.isEmpty(accountHolder) || StringUtils.isEmpty(accountNumber)
                || StringUtils.isEmpty(sortCode);
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
