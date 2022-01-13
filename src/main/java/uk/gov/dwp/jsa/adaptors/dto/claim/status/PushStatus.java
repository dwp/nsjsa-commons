package uk.gov.dwp.jsa.adaptors.dto.claim.status;

public class PushStatus {
    private PushStatusType status;
    private String substatus;

    public PushStatus() {
    }

    public PushStatus(final PushStatusType status, final String substatus) {
        this.status = status;
        this.substatus = substatus;
    }

    public void setStatus(final PushStatusType status) {
        this.status = status;
    }

    public void setSubstatus(final String substatus) {
        this.substatus = substatus;
    }

    public PushStatusType getStatus() {
        return status;
    }

    public String getSubstatus() {
        return substatus;
    }
}
