package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Pensions {

    private List<PensionDetail> current = new ArrayList<>();

    @JsonIgnore
    private List<PensionDetail> deferred = new ArrayList<>();

    @JsonIgnore
    private List<PensionDetail> future = new ArrayList<>();

    private Boolean hasExtraPensions;

    public Pensions(final List<PensionDetail> current, final List<PensionDetail> deferred,
                    final List<PensionDetail> future, final Boolean hasExtraPensions) {
        this.current = current;
        this.deferred = deferred;
        this.future = future;
        this.hasExtraPensions = hasExtraPensions;
    }

    public Pensions() {
    }

    public Boolean getHasExtraPensions() {
        return hasExtraPensions;
    }

    public List<PensionDetail> getCurrent() {
        return current;
    }

    public void setCurrent(final List<PensionDetail> current) {
        this.current = current;
    }

    public List<PensionDetail> getDeferred() {
        return deferred;
    }

    public void setDeferred(final List<PensionDetail> deferred) {
        this.deferred = deferred;
    }

    public List<PensionDetail> getFuture() {
        return future;
    }

    public void setFuture(final List<PensionDetail> future) {
        this.future = future;
    }

    public Boolean isHasExtraPensions() {
        return hasExtraPensions;
    }

    public void setHasExtraPensions(final Boolean hasExtraPensions) {
        this.hasExtraPensions = hasExtraPensions;
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
