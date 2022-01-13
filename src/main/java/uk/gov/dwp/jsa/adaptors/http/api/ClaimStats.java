package uk.gov.dwp.jsa.adaptors.http.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dwp.jsa.adaptors.dto.claim.ClaimStatistics;

public class ClaimStats {
    private ClaimStatistics claimStatistics;

    public ClaimStatistics getClaimStatistics() {
        return claimStatistics;
    }

    public void setClaimStatistics(final ClaimStatistics claimStatistics) {
        this.claimStatistics = claimStatistics;
    }

    @JsonCreator
    public ClaimStats(@JsonProperty("claimStats") final ClaimStatistics claimStatistics) {
        this.claimStatistics = claimStatistics;
    }
}
