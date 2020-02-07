package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import java.time.LocalDate;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Education {

    private String courseName;

    private Integer hoursPerWeek;

    private LocalDate startDate;

    private LocalDate endDate;

    private String institutionName;

    public Education() {
    }

    public Education(final String courseName,
                     final int hoursPerWeek,
                     final LocalDate startDate,
                     final LocalDate endDate,
                     final String institutionName) {
        this.courseName = courseName;
        this.hoursPerWeek = hoursPerWeek;
        this.startDate = startDate;
        this.endDate = endDate;
        this.institutionName = institutionName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(final String courseName) {
        this.courseName = courseName;
    }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(final Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
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

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(final String institutionName) {
        this.institutionName = institutionName;
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
