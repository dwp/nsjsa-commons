package uk.gov.dwp.jsa.adaptors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "services")
public class ServicesProperties {

    private String agentUiServer;
    private String auditServer;
    private String bankDetailsServer;
    private String bankDetailsVersion;
    private String circumstancesServer;
    private String circumstancesVersion;
    private String citizenUiServer;
    private String claimantServer;
    private String claimantVersion;
    private String jsapsServer;
    private String jsapsVersion;
    private String notificationServer;
    private String notificationVersion;
    private String officeSearchServer;
    private String officeSearchVersion;
    private String publicKey;
    private Map<String, String> publicKeyMap;
    private String statementServer;
    private String validationServer;
    private String validationVersion;

    public ServicesProperties() {
        //Default constructor.
    }

    public String getAgentUiServer() {
        return agentUiServer;
    }

    public void setAgentUiServer(final String agentUiServer) {
        this.agentUiServer = agentUiServer;
    }

    public String getAuditServer() {
        return auditServer;
    }

    public void setAuditServer(final String auditServer) {
        this.auditServer = auditServer;
    }

    public String getBankDetailsServer() {
        return bankDetailsServer;
    }

    public void setBankDetailsServer(final String bankDetailsServer) {
        this.bankDetailsServer = bankDetailsServer;
    }

    public String getBankDetailsVersion() {
        return bankDetailsVersion;
    }

    public void setBankDetailsVersion(final String bankDetailsVersion) {
        this.bankDetailsVersion = bankDetailsVersion;
    }

    public String getCircumstancesServer() {
        return circumstancesServer;
    }

    public void setCircumstancesServer(final String circumstancesServer) {
        this.circumstancesServer = circumstancesServer;
    }

    public String getCircumstancesVersion() {
        return circumstancesVersion;
    }

    public void setCircumstancesVersion(final String circumstancesVersion) {
        this.circumstancesVersion = circumstancesVersion;
    }


    public String getCitizenUiServer() {
        return citizenUiServer;
    }

    public void setCitizenUiServer(final String citizenUiServer) {
        this.citizenUiServer = citizenUiServer;
    }

    public String getClaimantServer() {
        return claimantServer;
    }

    public void setClaimantServer(final String claimantServer) {
        this.claimantServer = claimantServer;
    }

    public String getClaimantVersion() {
        return claimantVersion;
    }

    public void setClaimantVersion(final String claimantVersion) {
        this.claimantVersion = claimantVersion;
    }

    public String getJsapsServer() {
        return jsapsServer;
    }

    public void setJsapsServer(final String jsapsServer) {
        this.jsapsServer = jsapsServer;
    }

    public String getJsapsVersion() {
        return jsapsVersion;
    }

    public void setJsapsVersion(final String jsapsVersion) {
        this.jsapsVersion = jsapsVersion;
    }

    public String getNotificationServer() {
        return notificationServer;
    }

    public void setNotificationServer(final String notificationServer) {
        this.notificationServer = notificationServer;
    }

    public String getNotificationVersion() {
        return notificationVersion;
    }

    public void setNotificationVersion(final String notificationVersion) {
        this.notificationVersion = notificationVersion;
    }

    public String getOfficeSearchServer() {
        return officeSearchServer;
    }

    public void setOfficeSearchServer(final String officeSearchServer) {
        this.officeSearchServer = officeSearchServer;
    }

    public String getOfficeSearchVersion() {
        return officeSearchVersion;
    }

    public void setOfficeSearchVersion(final String officeSearchVersion) {
        this.officeSearchVersion = officeSearchVersion;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(final String publicKey) {
        this.publicKey = publicKey;
    }

    public String getStatementServer() {
        return statementServer;
    }

    public void setStatementServer(final String statementServer) {
        this.statementServer = statementServer;
    }

    public String getValidationServer() {
        return validationServer;
    }

    public void setValidationServer(final String validationServer) {
        this.validationServer = validationServer;
    }

    public String getValidationVersion() {
        return validationVersion;
    }

    public void setValidationVersion(final String validationVersion) {
        this.validationVersion = validationVersion;
    }

    public Map<String, String> getPublicKeyMap() {
        return publicKeyMap;
    }

    public void setPublicKeyMap(final Map<String, String> publicKeyMap) {
        this.publicKeyMap = publicKeyMap;
    }

}
