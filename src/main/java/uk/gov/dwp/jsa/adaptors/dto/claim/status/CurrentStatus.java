package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import java.time.LocalDateTime;

public class CurrentStatus {

    private BookingStatus bookingStatus = new BookingStatus();
    private PushStatus pushStatus = new PushStatus();
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;

    public CurrentStatus() {
    }

    public CurrentStatus(
            final BookingStatus bookingStatus,
            final PushStatus pushStatus,
            final LocalDateTime createdTimestamp) {
        this.bookingStatus = bookingStatus;
        this.pushStatus = pushStatus;
        this.createdTimestamp = createdTimestamp;
        this.updatedTimestamp = createdTimestamp;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(final BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }


    public LocalDateTime getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(final LocalDateTime updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public PushStatus getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(final PushStatus pushStatus) {
        this.pushStatus = pushStatus;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(final LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
