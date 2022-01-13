package uk.gov.dwp.jsa.adaptors.dto;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Nino {
    private String nino;

    public Nino(final String nino) {

        this.nino = nino;
    }

    public String getNino() {
        return nino;
    }

    public void setNino(final String nino) {
        this.nino = nino;
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
