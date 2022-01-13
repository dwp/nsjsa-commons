package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import uk.gov.dwp.jsa.adaptors.Constants;

public enum PushStatusType {
    PUSHED(Constants.LAST),
    NOT_PUSHED(Constants.LAST),
    CLERICAL(Constants.LAST),
    PUSH_FAILED(Constants.LAST),
    CANCELLED(Constants.LAST);

    private final int statusId;

    PushStatusType(final int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }
}
