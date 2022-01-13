package uk.gov.dwp.jsa.adaptors.dto.claim.status;

public class BookingStatus {

    private BookingStatusType status;
    private String substatus;
    private String agent;
    private String jobCentreCode;

    public BookingStatus() {
    }

    public BookingStatus(final BookingStatusType status,
                         final String substatus,
                         final String agent,
                         final String jobCentreCode) {
        this.status = status;
        this.substatus = substatus;
        this.agent = agent;
        this.jobCentreCode = jobCentreCode;
    }

    public void setStatus(final BookingStatusType status) {
        this.status = status;
    }

    public void setSubstatus(final String substatus) {
        this.substatus = substatus;
    }

    public BookingStatusType getStatus() {
        return status;
    }

    public String getSubstatus() {
        return substatus;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(final String agent) {
        this.agent = agent;
    }

    public String getJobCentreCode() {
        return jobCentreCode;
    }

    public void setJobCentreCode(final String jobCentreCode) {
        this.jobCentreCode = jobCentreCode;
    }
}
