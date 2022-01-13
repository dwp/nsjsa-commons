package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class Status {
    public static final String AGENT_PLACEHOLDER = "Agent";

    private UUID id;

    @JsonProperty("status")
    private BookingStatusType statusType;

    private String substatus;

    private String jobCentreCode;

    private String agent;

    private LocalDateTime createdTimestamp;

    public Status() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public BookingStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(final BookingStatusType statusType) {
        this.statusType = statusType;
    }

    public String getSubstatus() {
        return substatus;
    }

    public void setSubstatus(final String substatus) {
        this.substatus = substatus;
    }

    public String getJobCentreCode() {
        return jobCentreCode;
    }

    public void setJobCentreCode(final String jobCentreCode) {
        this.jobCentreCode = jobCentreCode;
    }

    public String getAgent() {
        if (agent == null || agent.isEmpty()) {
            return AGENT_PLACEHOLDER;
        }
        return agent;
    }

    public void setAgent(final String agent) {
        this.agent = agent;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(final LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
