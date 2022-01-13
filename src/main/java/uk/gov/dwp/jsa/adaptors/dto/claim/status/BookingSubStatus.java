package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import uk.gov.dwp.jsa.adaptors.Constants;

public enum BookingSubStatus {

    TOO_EARLY_FIRST_PERIOD(1), // 1st
    TOO_EARLY_SECOND_PERIOD(2), // 2nd
    TOO_LATE(1), // 1st
    PV_FLAG(2), // 2nd
    CALLBACK_1HR(2), //2nd
    CALLBACK_2HR(3), //3rd
    CALLBACK_3HR(4), //4th
    FAIL_TO_ATTEND(2), //2nd
    LANDLINE_ONLY(2), //2nd
    WRONG_NINO(2), //2nd
    VOICEMAIL(Constants.LAST),
    NO_VOICEMAIL(Constants.LAST),
    APPOINTEE_LETTER(Constants.LAST);

    private final int substatusOrderId;

    BookingSubStatus(final int substatusOrderId) {
        this.substatusOrderId = substatusOrderId;
    }

    public int getSubstatusOrderId() {
        return substatusOrderId;
    }
}
