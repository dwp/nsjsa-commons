package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class PensionsTest {

    public static final ArrayList<PensionDetail> CURRENT = new ArrayList<>();
    public static final ArrayList<PensionDetail> DEFERRED = new ArrayList<>();
    public static final ArrayList<PensionDetail> FUTURE = new ArrayList<>();
    public static final boolean HAS_EXTRA_PENSIONS = true;

    @Test
    public void hasDefaultFieldValues() {
        final Pensions pensions = new Pensions();
        assertThat(pensions.getCurrent(), is(CURRENT));
        assertThat(pensions.getDeferred(), is(DEFERRED));
        assertThat(pensions.getHasExtraPensions(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final Pensions pensions = new Pensions(CURRENT, DEFERRED, FUTURE, HAS_EXTRA_PENSIONS);
        assertThat(pensions.getCurrent(), is(CURRENT));
        assertThat(pensions.getDeferred(), is(DEFERRED));
        assertThat(pensions.getHasExtraPensions(), is(HAS_EXTRA_PENSIONS));
    }

    @Test
    public void setCurrent() {
        final Pensions pensions = new Pensions();
        pensions.setCurrent(CURRENT);
        assertThat(pensions.getCurrent(), is(CURRENT));
    }

    @Test
    public void setDeferred() {
        final Pensions pensions = new Pensions();
        pensions.setDeferred(DEFERRED);
        assertThat(pensions.getDeferred(), is(DEFERRED));
    }

    @Test
    public void setHasExtraPensions() {
        final Pensions pensions = new Pensions();
        pensions.setHasExtraPensions(HAS_EXTRA_PENSIONS);
        assertThat(pensions.getHasExtraPensions(), is(HAS_EXTRA_PENSIONS));
    }
}
