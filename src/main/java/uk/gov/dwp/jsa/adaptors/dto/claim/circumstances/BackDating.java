package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

public class BackDating {

    private Boolean wereYouAvailableForWork;
    private Boolean wereYouSearchingForWork;
    private String whyNotApplySooner;
    private Boolean inPaidWorkSince;
    private Boolean inFullTimeEducationSince;
    private AskedForAdvice askedForAdvice;
    private NonWorkingIllness nonWorkingIllness;
    private TravelledOutsideUk travelledOutsideUk;

    public BackDating() {
    }

    public BackDating(
            final Boolean wereYouAvailableForWork,
            final Boolean wereYouSearchingForWork,
            final String whyNotApplySooner,
            final Boolean inPaidWorkSince,
            final Boolean inFullTimeEducationSince,
            final AskedForAdvice askedForAdvice,
            final NonWorkingIllness nonWorkingIllness,
            final TravelledOutsideUk travelledOutsideUk
    ) {
        this.wereYouAvailableForWork = wereYouAvailableForWork;
        this.wereYouSearchingForWork = wereYouSearchingForWork;
        this.whyNotApplySooner = whyNotApplySooner;
        this.inPaidWorkSince = inPaidWorkSince;
        this.inFullTimeEducationSince = inFullTimeEducationSince;
        this.askedForAdvice = askedForAdvice;
        this.nonWorkingIllness = nonWorkingIllness;
        this.travelledOutsideUk = travelledOutsideUk;
    }

    public Boolean getWereYouAvailableForWork() {
        return wereYouAvailableForWork;
    }

    public void setWereYouAvailableForWork(final Boolean wereYouAvailableForWork) {
        this.wereYouAvailableForWork = wereYouAvailableForWork;
    }

    public Boolean getWereYouSearchingForWork() {
        return wereYouSearchingForWork;
    }

    public void setWereYouSearchingForWork(final Boolean wereYouSearchingForWork) {
        this.wereYouSearchingForWork = wereYouSearchingForWork;
    }

    public String getWhyNotApplySooner() {
        return whyNotApplySooner;
    }

    public void setWhyNotApplySooner(final String whyNotApplySooner) {
        this.whyNotApplySooner = whyNotApplySooner;
    }

    public Boolean getInPaidWorkSince() {
        return inPaidWorkSince;
    }

    public void setInPaidWorkSince(final Boolean inPaidWorkSince) {
        this.inPaidWorkSince = inPaidWorkSince;
    }

    public Boolean getInFullTimeEducationSince() {
        return inFullTimeEducationSince;
    }

    public void setInFullTimeEducationSince(final Boolean inFullTimeEducationSince) {
        this.inFullTimeEducationSince = inFullTimeEducationSince;
    }

    public AskedForAdvice getAskedForAdvice() {
        return askedForAdvice;
    }

    public void setAskedForAdvice(final AskedForAdvice askedForAdvice) {
        this.askedForAdvice = askedForAdvice;
    }

    public NonWorkingIllness getNonWorkingIllness() {
        return nonWorkingIllness;
    }

    public void setNonWorkingIllness(final NonWorkingIllness nonWorkingIllness) {
        this.nonWorkingIllness = nonWorkingIllness;
    }

    public TravelledOutsideUk getTravelledOutsideUk() {
        return travelledOutsideUk;
    }

    public void setTravelledOutsideUk(final TravelledOutsideUk travelledOutsideUk) {
        this.travelledOutsideUk = travelledOutsideUk;
    }
}
