package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class CircumstancesTest {

    public static final UUID ID = UUID.randomUUID();
    public static final LocalDate CLAIM_START_DATE = LocalDate.now();
    public static final UUID CLAIMANT_ID = UUID.randomUUID();
    public static final LocalDate DATE_OF_CLAIM = LocalDate.now();
    public static final boolean DECLARATION_AGREED = true;
    public static final boolean HAS_NON_UK_WORK_BENEFIT = true;
    public static final boolean HAS_EXTRA_PREVIOUS_WORK = true;
    public static final boolean HAS_EXTRA_CURRENT_WORK = true;
    public static final ArrayList<CurrentWork> CURRENT_WORK = new ArrayList<>();
    public static final ArrayList<PreviousWork> PREVIOUS_WORK = new ArrayList<>();
    public static final Education EDUCATION = new Education();
    public static final JuryService JURY_SERVICE = new JuryService();
    public static final AvailableForInterview AVAILABLE_FOR_INTERVIEW = new AvailableForInterview();
    public static final OtherBenefit OTHER_BENEFIT = new OtherBenefit();
    public static final Pensions PENSIONS = new Pensions();
    public static final String LOCALE = "EN_GB";
    public static final String SERVICE_VERSION = "v1.0";
    public static final BackDating BACK_DATING = new BackDating();
    public static final boolean ENCRYPTED = true;

    @Test
    public void hasDefaultFieldValues() {
        final Circumstances circumstances = new Circumstances();
        assertThat(circumstances.getId(), is(nullValue()));
        assertThat(circumstances.getClaimStartDate(), is(nullValue()));
        assertThat(circumstances.getClaimantId(), is(nullValue()));
        assertThat(circumstances.getDateOfClaim(), is(nullValue()));
        assertThat(circumstances.getDeclarationAgreed(), is(nullValue()));
        assertThat(circumstances.getHasNonUKWorkBenefit(), is(nullValue()));
        assertThat(circumstances.getHasExtraPreviousWork(), is(nullValue()));
        assertThat(circumstances.getHasExtraCurrentWork(), is(nullValue()));
        assertThat(circumstances.getCurrentWork(), is(CURRENT_WORK));
        assertThat(circumstances.getPreviousWork(), is(PREVIOUS_WORK));
        assertThat(circumstances.getEducation(), is(nullValue()));
        assertThat(circumstances.getJuryService(), is(nullValue()));
        assertThat(circumstances.getAvailableForInterview(), is(AVAILABLE_FOR_INTERVIEW));
        assertThat(circumstances.getOtherBenefit(), is(nullValue()));
        assertThat(circumstances.getPensions(), is(PENSIONS));
        assertThat(circumstances.getLocale(), is(nullValue()));
        assertThat(circumstances.getServiceVersion(), is(nullValue()));
        assertThat(circumstances.getBackDating(), is(nullValue()));
    }

    @Test
    public void constructorSetsFieldValues() {
        final Circumstances circumstances = new Circumstances(
                ID, CLAIMANT_ID, CLAIM_START_DATE, DATE_OF_CLAIM, DECLARATION_AGREED, HAS_NON_UK_WORK_BENEFIT,
                CURRENT_WORK, PREVIOUS_WORK, EDUCATION, JURY_SERVICE, AVAILABLE_FOR_INTERVIEW, OTHER_BENEFIT, PENSIONS,
                HAS_EXTRA_CURRENT_WORK, HAS_EXTRA_PREVIOUS_WORK, LOCALE, BACK_DATING, ENCRYPTED);
        assertThat(circumstances.getId(), is(ID));
        assertThat(circumstances.getClaimStartDate(), is(CLAIM_START_DATE));
        assertThat(circumstances.getClaimantId(), is(CLAIMANT_ID));
        assertThat(circumstances.getDateOfClaim(), is(DATE_OF_CLAIM));
        assertThat(circumstances.getDeclarationAgreed(), is(DECLARATION_AGREED));
        assertThat(circumstances.getHasNonUKWorkBenefit(), is(HAS_NON_UK_WORK_BENEFIT));
        assertThat(circumstances.getHasExtraPreviousWork(), is(HAS_EXTRA_PREVIOUS_WORK));
        assertThat(circumstances.getHasExtraCurrentWork(), is(HAS_EXTRA_CURRENT_WORK));
        assertThat(circumstances.getCurrentWork(), is(CURRENT_WORK));
        assertThat(circumstances.getPreviousWork(), is(PREVIOUS_WORK));
        assertThat(circumstances.getEducation(), is(EDUCATION));
        assertThat(circumstances.getJuryService(), is(JURY_SERVICE));
        assertThat(circumstances.getAvailableForInterview(), is(AVAILABLE_FOR_INTERVIEW));
        assertThat(circumstances.getOtherBenefit(), is(OTHER_BENEFIT));
        assertThat(circumstances.getPensions(), is(PENSIONS));
        assertThat(circumstances.getLocale(), is(LOCALE));
        assertThat(circumstances.getServiceVersion(), is(nullValue()));
        assertThat(circumstances.getBackDating(), is(BACK_DATING));
    }

    @Test
    public void setId() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setId(ID);
        assertThat(circumstances.getId(), is(ID));
    }

    @Test
    public void setClaimStartDate() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setClaimStartDate(CLAIM_START_DATE);
        assertThat(circumstances.getClaimStartDate(), is(CLAIM_START_DATE));
    }

    @Test
    public void setClaimantId() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setClaimantId(CLAIMANT_ID);
        assertThat(circumstances.getClaimantId(), is(CLAIMANT_ID));
    }

    @Test
    public void setDateOfClaim() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setDateOfClaim(DATE_OF_CLAIM);
        assertThat(circumstances.getDateOfClaim(), is(DATE_OF_CLAIM));
    }

    @Test
    public void setDeclarationAgreed() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setDeclarationAgreed(DECLARATION_AGREED);
        assertThat(circumstances.getDeclarationAgreed(), is(DECLARATION_AGREED));
    }

    @Test
    public void setHasNonUKWorkBenefit() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setHasNonUKWorkBenefit(HAS_NON_UK_WORK_BENEFIT);
        assertThat(circumstances.getHasNonUKWorkBenefit(), is(HAS_NON_UK_WORK_BENEFIT));
    }

    @Test
    public void setHasExtraPreviousWork() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setHasExtraPreviousWork(HAS_EXTRA_PREVIOUS_WORK);
        assertThat(circumstances.getHasExtraPreviousWork(), is(HAS_EXTRA_PREVIOUS_WORK));
    }

    @Test
    public void setHasExtraCurrentWork() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setHasExtraCurrentWork(HAS_EXTRA_CURRENT_WORK);
        assertThat(circumstances.getHasExtraCurrentWork(), is(HAS_EXTRA_CURRENT_WORK));
    }

    @Test
    public void setCurrentWork() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setCurrentWork(CURRENT_WORK);
        assertThat(circumstances.getCurrentWork(), is(CURRENT_WORK));
    }

    @Test
    public void setPreviousWork() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setPreviousWork(PREVIOUS_WORK);
        assertThat(circumstances.getPreviousWork(), is(PREVIOUS_WORK));
    }

    @Test
    public void setEducation() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setEducation(EDUCATION);
        assertThat(circumstances.getEducation(), is(EDUCATION));
    }

    @Test
    public void setJuryService() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setJuryService(JURY_SERVICE);
        assertThat(circumstances.getJuryService(), is(JURY_SERVICE));
    }

    @Test
    public void setAvailableForInterview() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setAvailableForInterview(AVAILABLE_FOR_INTERVIEW);
        assertThat(circumstances.getAvailableForInterview(), is(AVAILABLE_FOR_INTERVIEW));
    }

    @Test
    public void setOtherBenefit() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setOtherBenefit(OTHER_BENEFIT);
        assertThat(circumstances.getOtherBenefit(), is(OTHER_BENEFIT));
    }

    @Test
    public void setPensions() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setPensions(PENSIONS);
        assertThat(circumstances.getPensions(), is(PENSIONS));
    }

    @Test
    public void setLocale() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setLocale(LOCALE);
        assertThat(circumstances.getLocale(), is(LOCALE));
    }

    @Test
    public void setServiceVersion() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setServiceVersion(SERVICE_VERSION);
        assertThat(circumstances.getServiceVersion(), is(SERVICE_VERSION));
    }

    @Test
    public void setBackDating() {
        final Circumstances circumstances = new Circumstances();
        circumstances.setBackDating(BACK_DATING);
        assertThat(circumstances.getBackDating(), is(BACK_DATING));
    }
}
