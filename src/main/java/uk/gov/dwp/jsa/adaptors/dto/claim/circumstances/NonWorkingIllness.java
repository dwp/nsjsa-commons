package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import java.time.LocalDate;

public class NonWorkingIllness {
    private Boolean hadIllness;
    private LocalDate startDate;
    private LocalDate endDate;

    public NonWorkingIllness() {
    }

    public NonWorkingIllness(final Boolean hadIllness, final LocalDate startDate, final LocalDate endDate) {
        this.hadIllness = hadIllness;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Boolean getHadIllness() {
        return hadIllness;
    }

    public void setHadIllness(final Boolean hadIllness) {
        this.hadIllness = hadIllness;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }
}
