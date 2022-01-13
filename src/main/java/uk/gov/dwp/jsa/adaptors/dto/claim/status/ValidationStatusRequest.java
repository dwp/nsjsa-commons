package uk.gov.dwp.jsa.adaptors.dto.claim.status;

public class ValidationStatusRequest {
    private BookingStatus bookingStatus;
    private PushStatus pushStatus;

    public ValidationStatusRequest(final BookingStatus bookingStatus,
                                   final PushStatus pushStatus) {
        this.bookingStatus = bookingStatus;
        this.pushStatus = pushStatus;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(final BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public PushStatus getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(final PushStatus pushStatus) {
        this.pushStatus = pushStatus;
    }
}
