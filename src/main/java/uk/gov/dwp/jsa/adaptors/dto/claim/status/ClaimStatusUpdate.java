package uk.gov.dwp.jsa.adaptors.dto.claim.status;

public class ClaimStatusUpdate {
    private BookingStatus bookingStatus;

    public ClaimStatusUpdate() {
    }

    public ClaimStatusUpdate(final BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(final BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
