package uk.gov.dwp.jsa.adaptors.dto.claim;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ContactDetails {
    private String number;
    private String email;
    private Boolean numberProvided;
    private Boolean emailProvided;

    public ContactDetails(final String number, final String email, final Boolean numberProvided,
                          final Boolean emailProvided) {
        this.number = number;
        this.email = email;
        this.numberProvided = numberProvided;
        this.emailProvided = emailProvided;
    }

    public ContactDetails() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Boolean getNumberProvided() {
        return numberProvided;
    }

    public void setNumberProvided(final Boolean numberProvided) {
        this.numberProvided = numberProvided;
    }

    public Boolean getEmailProvided() {
        return emailProvided;
    }

    public void setEmailProvided(final Boolean emailProvided) {
        this.emailProvided = emailProvided;
    }

    @Override
    public boolean equals(final Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }


}
