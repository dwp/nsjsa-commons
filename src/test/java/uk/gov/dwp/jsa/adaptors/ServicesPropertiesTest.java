package uk.gov.dwp.jsa.adaptors;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ServicesPropertiesTest {

    private static final String CLAIMANT_SERVER = "CLAIMANT_SERVER";
    private static final String CLAIMANT_VERSION = "CLAIMANT_VERSION";
    private static final String CIRCUMSTANCES_SERVER = "CIRCUMSTANCES_SERVER";
    private static final String CIRCUMSTANCES_VERSION = "CIRCUMSTANCES_VERSION";
    private static final String BANK_DETAILS_SERVER = "BANK_DETAILS_SERVER";
    private static final String BANK_DETAILS_VERSION = "BANK_DETAILS_VERSION";
    public static final String JSAPS_SERVER = "JSAPS_SERVER";
    public static final String JSAPS_VERSION = "JSAPS_VERSION";
    public static final String NOTIFICATION_SERVER = "NOTIFICATION_SERVER";
    public static final String NOTIFICATION_VERSION = "NOTIFICATION_VERSION";
    public static final String OFFICE_SEARCH_SERVER = "OFFICE_SEARCH_SERVER";
    public static final String OFFICE_SEARCH_VERSION = "OFFICE_SEARCH_VERSION";
    public static final String VALIDATION_SERVER = "VALIDATION_SERVER";
    public static final String VALIDATION_VERSION = "VALIDATION_VERSION";
    public static final String STATEMENT_SERVER = "STATEMENT_SERVER";
    public static final String AUDIT_SERVER = "AUDIT_SERVER";
    public static final String AGENT_UI_SERVER = "AGENT_UI_SERVER";
    public static final String CITIZEN_UI_SERVER = "CITIZEN_UI_SERVER";
    public static final String PUBLIC_KEY = "PUBLIC_KEY";
    public static final HashMap<String, String> PUBLIC_KEY_MAP = new HashMap<>();


    @Test
    public void setsPublicKeyMap() {
        ServicesProperties properties = new ServicesProperties();
        properties.setPublicKeyMap(PUBLIC_KEY_MAP);
        assertThat(properties.getPublicKeyMap(), is(PUBLIC_KEY_MAP));
    }

    @Test
    public void setsPublicKey() {
        ServicesProperties properties = new ServicesProperties();
        properties.setPublicKey(PUBLIC_KEY);
        assertThat(properties.getPublicKey(), is(PUBLIC_KEY));
    }

    @Test
    public void setsCitizenUiServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setCitizenUiServer(CITIZEN_UI_SERVER);
        assertThat(properties.getCitizenUiServer(), is(CITIZEN_UI_SERVER));
    }

    @Test
    public void setsAgentUiServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setAgentUiServer(AGENT_UI_SERVER);
        assertThat(properties.getAgentUiServer(), is(AGENT_UI_SERVER));
    }

    @Test
    public void setsAuditServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setAuditServer(AUDIT_SERVER);
        assertThat(properties.getAuditServer(), is(AUDIT_SERVER));
    }

    @Test
    public void setsStatementServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setStatementServer(STATEMENT_SERVER);
        assertThat(properties.getStatementServer(), is(STATEMENT_SERVER));
    }

    @Test
    public void setsValidationServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setValidationServer(VALIDATION_SERVER);
        assertThat(properties.getValidationServer(), is(VALIDATION_SERVER));
    }

    @Test
    public void setsValidationVersion() {
        ServicesProperties properties = new ServicesProperties();
        properties.setValidationVersion(VALIDATION_VERSION);
        assertThat(properties.getValidationVersion(), is(VALIDATION_VERSION));
    }

    @Test
    public void setsOfficeSearchServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setOfficeSearchServer(OFFICE_SEARCH_SERVER);
        assertThat(properties.getOfficeSearchServer(), is(OFFICE_SEARCH_SERVER));
    }

    @Test
    public void setsOfficeSearchVersion() {
        ServicesProperties properties = new ServicesProperties();
        properties.setOfficeSearchVersion(OFFICE_SEARCH_VERSION);
        assertThat(properties.getOfficeSearchVersion(), is(OFFICE_SEARCH_VERSION));
    }

    @Test
    public void setsNotificationServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setNotificationServer(NOTIFICATION_SERVER);
        assertThat(properties.getNotificationServer(), is(NOTIFICATION_SERVER));
    }

    @Test
    public void setsNotificationVersion() {
        ServicesProperties properties = new ServicesProperties();
        properties.setNotificationVersion(NOTIFICATION_VERSION);
        assertThat(properties.getNotificationVersion(), is(NOTIFICATION_VERSION));
    }

    @Test
    public void setsJsapsServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setJsapsServer(JSAPS_SERVER);
        assertThat(properties.getJsapsServer(), is(JSAPS_SERVER));
    }

    @Test
    public void setsJsapsVersion() {
        ServicesProperties properties = new ServicesProperties();
        properties.setJsapsVersion(JSAPS_VERSION);
        assertThat(properties.getJsapsVersion(), is(JSAPS_VERSION));
    }


    @Test
    public void setsClaimantServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setClaimantServer(CLAIMANT_SERVER);
        assertThat(properties.getClaimantServer(), is(CLAIMANT_SERVER));
    }

    @Test
    public void setsBankDetailsServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setBankDetailsServer(BANK_DETAILS_SERVER);
        assertThat(properties.getBankDetailsServer(), is(BANK_DETAILS_SERVER));
    }

    @Test
    public void setsBankDetailsVersion() {
        ServicesProperties properties = new ServicesProperties();
        properties.setBankDetailsVersion(BANK_DETAILS_VERSION);
        assertThat(properties.getBankDetailsVersion(), is(BANK_DETAILS_VERSION));
    }

    @Test
    public void setsClaimantVersion() {
        ServicesProperties properties = new ServicesProperties();
        properties.setClaimantVersion(CLAIMANT_VERSION);
        assertThat(properties.getClaimantVersion(), is(CLAIMANT_VERSION));
    }

    @Test
    public void setsCircumstancesServer() {
        ServicesProperties properties = new ServicesProperties();
        properties.setCircumstancesServer(CIRCUMSTANCES_SERVER);
        assertThat(properties.getCircumstancesServer(), is(CIRCUMSTANCES_SERVER));
    }

    @Test
    public void setsCircumstancesVersion() {
        ServicesProperties properties = new ServicesProperties();
        properties.setCircumstancesVersion(CIRCUMSTANCES_VERSION);
        assertThat(properties.getCircumstancesVersion(), is(CIRCUMSTANCES_VERSION));
    }
}
