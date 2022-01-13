package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

public class AskedForAdvice {
    private String advice;
    private Boolean hasAsked;

    public AskedForAdvice() {
    }

    public AskedForAdvice(final String advice, final Boolean hasAsked) {
        this.advice = advice;
        this.hasAsked = hasAsked;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(final String advice) {
        this.advice = advice;
    }

    public Boolean getHasAsked() {
        return hasAsked;
    }

    public void setHasAsked(final Boolean hasAsked) {
        this.hasAsked = hasAsked;
    }
}
