package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import java.time.LocalDate;

public class TravelledOutsideUk {
    private Boolean hadTravelled;
    private LocalDate startDate;
    private LocalDate endDate;

    public TravelledOutsideUk() {
    }

    public TravelledOutsideUk(final Boolean hadTravelled, final LocalDate startDate, final LocalDate endDate) {
        this.hadTravelled = hadTravelled;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Boolean getHadTravelled() {
        return hadTravelled;
    }

    public void setHadTravelled(final Boolean hadTravelled) {
        this.hadTravelled = hadTravelled;
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
