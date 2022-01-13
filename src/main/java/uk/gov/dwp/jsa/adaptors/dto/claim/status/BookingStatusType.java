package uk.gov.dwp.jsa.adaptors.dto.claim.status;


import uk.gov.dwp.jsa.adaptors.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.APPOINTEE_LETTER;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.CALLBACK_1HR;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.CALLBACK_2HR;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.CALLBACK_3HR;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.FAIL_TO_ATTEND;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.LANDLINE_ONLY;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.NO_VOICEMAIL;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.PV_FLAG;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.TOO_EARLY_FIRST_PERIOD;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.TOO_EARLY_SECOND_PERIOD;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.TOO_LATE;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.VOICEMAIL;
import static uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingSubStatus.WRONG_NINO;

public enum BookingStatusType {
    PREVIEW(1, TOO_EARLY_FIRST_PERIOD, TOO_EARLY_SECOND_PERIOD, TOO_LATE, PV_FLAG),
    FIRST_FAIL(2, CALLBACK_1HR, CALLBACK_2HR, CALLBACK_3HR, FAIL_TO_ATTEND, LANDLINE_ONLY, WRONG_NINO),
    NEW_CLAIM(5),
    NO_SLOTS_AVAILABLE(6),
    FINAL_FAIL(Constants.LAST, VOICEMAIL, NO_VOICEMAIL, APPOINTEE_LETTER),
    SUCCESS(Constants.LAST),
    WITHDRAWN(Constants.LAST);

    private final List<BookingSubStatus> subStatuses;
    private final int statusId;

    BookingStatusType(final int statusId, final BookingSubStatus... subStatus) {
        this.statusId = statusId;
        if (Objects.nonNull(subStatus)) {
            this.subStatuses = Arrays.asList(subStatus);
        } else {
            this.subStatuses = emptyList();
        }
    }

    public static String getSubStatus(
            final BookingStatusType claimStatus,
            final BookingSubStatus[] claimSubStatuses) {

        if (!claimStatus.getSubStatuses().isEmpty()) {
            final List<BookingSubStatus> bookingSubStatuses = of(claimSubStatuses)
                    .filter(Objects::nonNull)
                    .collect(toList());

            Optional<BookingSubStatus> subStatusOptional = claimStatus
                    .getSubStatuses()
                    .stream()
                    .filter(s -> isSubStatusOfSelectedStatus(bookingSubStatuses, s))
                    .findFirst();
            if (subStatusOptional.isPresent()) {
                return subStatusOptional.get().name();
            }
         }
        return "";

    }

    public static boolean isSubStatusOfSelectedStatus(final List<BookingSubStatus> bookingSubStatuses,
                                                      final BookingSubStatus s) {
        return bookingSubStatuses.stream().anyMatch(claimSubStatus -> claimSubStatus.equals(s));
    }


    public List<BookingSubStatus> getSubStatuses() {
        return subStatuses;
    }

    public int getStatusId() {
        return statusId;
    }
}
