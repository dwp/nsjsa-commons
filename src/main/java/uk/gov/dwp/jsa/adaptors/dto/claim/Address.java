package uk.gov.dwp.jsa.adaptors.dto.claim;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Address {

    private String firstLine;

    private String secondLine;

    private String postCode;

    private String town;

    private String country;

    public Address(final String firstLine, final String secondLine, final String postCode,
                   final String town, final String country) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.postCode = postCode;
        this.town = town;
        this.country = country;
    }

    public Address() {
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(final String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(final String secondLine) {
        this.secondLine = secondLine;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(final String postCode) {
        this.postCode = postCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(final String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
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
