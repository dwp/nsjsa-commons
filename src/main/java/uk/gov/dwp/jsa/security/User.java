package uk.gov.dwp.jsa.security;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String fullName;

    private final String staffNumber;

    private final String payload;

    public User(final String fullName, final String staffNumber) {
        this(fullName, staffNumber, null);
    }

    public User(final String fullName, final String staffNumber, final String payload) {
        this.fullName = fullName;
        this.staffNumber = staffNumber;
        this.payload = payload;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public String getPayload() {
        return payload;
    }
}
