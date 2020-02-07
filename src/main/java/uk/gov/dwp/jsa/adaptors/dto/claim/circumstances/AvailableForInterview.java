package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.availability.Day;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class AvailableForInterview {

    private List<Day> daysNotAvailable = new ArrayList<>();

    public List<Day> getDaysNotAvailable() {
        return daysNotAvailable;
    }

    public void setDaysNotAvailable(final List<Day> daysNotAvailable) {
        this.daysNotAvailable = daysNotAvailable;
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
