package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Circumstances {

    private UUID id;

    // Request
    private UUID claimantId;

    private LocalDate claimStartDate;

    private LocalDate dateOfClaim;

    private Boolean declarationAgreed;

    private Boolean hasNonUKWorkBenefit;

    private Boolean hasExtraPreviousWork;

    private List<CurrentWork> currentWork = new ArrayList<>();

    private Boolean hasExtraCurrentWork;

    private List<PreviousWork> previousWork = new ArrayList<>();

    private Education education;

    private JuryService juryService;

    private AvailableForInterview availableForInterview = new AvailableForInterview();

    private OtherBenefit otherBenefit;

    private Pensions pensions = new Pensions();

    private String locale;

    private String serviceVersion;

    private BackDating backDating;

    private Boolean encrypted;

    public Circumstances() {
    }

    public Circumstances(
            final UUID id, final UUID claimantId,
            final LocalDate claimStartDate,
            final LocalDate dateOfClaim,
            final Boolean declarationAgreed,
            final Boolean hasNonUKWorkBenefit,
            final List<CurrentWork> currentWork,
            final List<PreviousWork> previousWork,
            final Education education,
            final JuryService juryService,
            final AvailableForInterview availableForInterview,
            final OtherBenefit otherBenefit,
            final Pensions pensions,
            final Boolean hasExtraCurrentWork,
            final Boolean hasExtraPreviousWork,
            final String locale,
            final BackDating backDating,
            final Boolean encrypted) {
        this.id = id;
        this.claimantId = claimantId;
        this.claimStartDate = claimStartDate;
        this.dateOfClaim = dateOfClaim;
        this.declarationAgreed = declarationAgreed;
        this.hasNonUKWorkBenefit = hasNonUKWorkBenefit;
        this.currentWork = currentWork;
        this.previousWork = previousWork;
        this.education = education;
        this.juryService = juryService;
        this.availableForInterview = availableForInterview;
        this.otherBenefit = otherBenefit;
        this.pensions = pensions;
        this.hasExtraCurrentWork = hasExtraCurrentWork;
        this.hasExtraPreviousWork = hasExtraPreviousWork;
        this.locale = locale;
        this.backDating = backDating;
        this.encrypted = encrypted;
    }

    public Boolean getDeclarationAgreed() {
        return declarationAgreed;
    }

    public Boolean getHasNonUKWorkBenefit() {
        return hasNonUKWorkBenefit;
    }

    public Boolean getHasExtraPreviousWork() {
        return hasExtraPreviousWork;
    }

    public Boolean getHasExtraCurrentWork() {
        return hasExtraCurrentWork;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public Boolean hasNonUKWorkBenefit() {
        return hasNonUKWorkBenefit;
    }

    public LocalDate getClaimStartDate() {
        return claimStartDate;
    }

    public void setClaimStartDate(final LocalDate claimStartDate) {
        this.claimStartDate = claimStartDate;
    }

    public UUID getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(final UUID claimantId) {
        this.claimantId = claimantId;
    }

    public LocalDate getDateOfClaim() {
        return dateOfClaim;
    }

    public void setDateOfClaim(final LocalDate dateOfClaim) {
        this.dateOfClaim = dateOfClaim;
    }

    public Boolean isDeclarationAgreed() {
        return declarationAgreed;
    }

    public void setDeclarationAgreed(final Boolean declarationAgreed) {
        this.declarationAgreed = declarationAgreed;
    }

    public Boolean isHasNonUKWorkBenefit() {
        return hasNonUKWorkBenefit;
    }

    public void setHasNonUKWorkBenefit(final Boolean hasNonUKWorkBenefit) {
        this.hasNonUKWorkBenefit = hasNonUKWorkBenefit;
    }

    public Boolean isHasExtraPreviousWork() {
        return hasExtraPreviousWork;
    }

    public void setHasExtraPreviousWork(final Boolean hasExtraPreviousWork) {
        this.hasExtraPreviousWork = hasExtraPreviousWork;
    }

    public Boolean isHasExtraCurrentWork() {
        return hasExtraCurrentWork;
    }

    public void setHasExtraCurrentWork(final Boolean hasExtraCurrentWork) {
        this.hasExtraCurrentWork = hasExtraCurrentWork;
    }

    public List<CurrentWork> getCurrentWork() {
        return currentWork;
    }

    public void setCurrentWork(final List<CurrentWork> currentWork) {
        this.currentWork = currentWork;
    }

    public List<PreviousWork> getPreviousWork() {
        return previousWork;
    }

    public void setPreviousWork(final List<PreviousWork> previousWork) {
        this.previousWork = previousWork;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(final Education education) {
        this.education = education;
    }

    public JuryService getJuryService() {
        return juryService;
    }

    public void setJuryService(final JuryService juryService) {
        this.juryService = juryService;
    }

    public AvailableForInterview getAvailableForInterview() {
        return availableForInterview;
    }

    public void setAvailableForInterview(final AvailableForInterview availableForInterview) {
        this.availableForInterview = availableForInterview;
    }

    public OtherBenefit getOtherBenefit() {
        return otherBenefit;
    }

    public void setOtherBenefit(final OtherBenefit otherBenefit) {
        this.otherBenefit = otherBenefit;
    }

    public Pensions getPensions() {
        return pensions;
    }

    public void setPensions(final Pensions pensions) {
        this.pensions = pensions;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(final String locale) {
        this.locale = locale;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(final String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public BackDating getBackDating() {
        return backDating;
    }

    public void setBackDating(final BackDating backDating) {
        this.backDating = backDating;
    }

    public Boolean getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(final Boolean encrypted) {
        this.encrypted = encrypted;
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
