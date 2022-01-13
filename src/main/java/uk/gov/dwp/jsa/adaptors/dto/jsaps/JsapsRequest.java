package uk.gov.dwp.jsa.adaptors.dto.jsaps;


import uk.gov.dwp.jsa.adaptors.dto.claim.BankDetails;
import uk.gov.dwp.jsa.adaptors.dto.claim.Claimant;
import uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.Circumstances;

import javax.validation.constraints.NotNull;


public class JsapsRequest {

    @NotNull
    private Boolean agentMode;

    @NotNull
    private String homeOfficeId;

    @NotNull
    private String esjNumber;

    @NotNull
    private String targetOfficeId;

    @NotNull
    private Circumstances circumstances;

    @NotNull
    private Claimant claimant;

    private BankDetails bankDetails;

    @NotNull
    private Boolean receivingUniversalCredit;

    public void setReceivingUniversalCredit(final Boolean receivingUniversalCredit) {
        this.receivingUniversalCredit = receivingUniversalCredit;
    }

    public Boolean getReceivingUniversalCredit() {
        return receivingUniversalCredit;
    }

    public String getEsjNumber() {
        return esjNumber;
    }

    public void setEsjNumber(final String esjNumber) {
        this.esjNumber = esjNumber;
    }

    public String getHomeOfficeId() {
        return homeOfficeId;
    }

    public void setHomeOfficeId(final String homeOfficeId) {
        this.homeOfficeId = homeOfficeId;
    }

    public String getTargetOfficeId() {
        return targetOfficeId;
    }

    public void setTargetOfficeId(final String targetOfficeId) {
        this.targetOfficeId = targetOfficeId;
    }

    public Circumstances getCircumstances() {
        return circumstances;
    }

    public void setCircumstances(final Circumstances circumstances) {
        this.circumstances = circumstances;
    }

    public Claimant getClaimant() {
        return claimant;
    }

    public void setClaimant(final Claimant claimant) {
        this.claimant = claimant;
    }

    public Boolean getAgentMode() {
        return agentMode;
    }

    public void setAgentMode(final Boolean agentMode) {
        this.agentMode = agentMode;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(final BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }
}
