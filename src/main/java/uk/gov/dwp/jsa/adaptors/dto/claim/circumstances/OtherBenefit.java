package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

public class OtherBenefit {

    private String description;

    public OtherBenefit() {
    }

    public OtherBenefit(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
