package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.availability;


import com.fasterxml.jackson.annotation.JsonIgnore;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

/**
 * Q88 Why you can't attend the job centre interview.
 */
public class Reason {

    private Boolean selected = false;

    @JsonIgnore
    private AttendInterviewReason reasonType;

    @JsonIgnore
    private String detail;

    public Reason() {
    }

    public Reason(final Boolean selected) {
        this.selected = selected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(final Boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(final Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }

    public AttendInterviewReason getReasonType() {
        return reasonType;
    }

    public void setReasonType(final AttendInterviewReason reasonType) {
        this.reasonType = reasonType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(final String detail) {
        this.detail = detail;
    }
}
