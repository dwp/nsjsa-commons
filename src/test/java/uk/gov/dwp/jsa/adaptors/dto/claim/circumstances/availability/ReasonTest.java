package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.availability;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ReasonTest {
    private static final Boolean SELECTED = true;
    private static final AttendInterviewReason REASON_TYPE = AttendInterviewReason.OTHER;
    public static final String DETAIL = "DETAIL";


    @Test
    public void constructorSetsFields() {
        final Reason reason = new Reason(SELECTED);
        assertThat(reason.getSelected(), is(SELECTED));
    }

    @Test
    public void constructorhasDefaults() {
        final Reason reason = new Reason();
        assertThat(reason.getSelected(), is(false));
    }


    @Test
    public void setSelected() {
        final Reason reason = new Reason();
        reason.setSelected(SELECTED);
        assertThat(reason.getSelected(), is(SELECTED));
        assertThat(reason.isSelected(), is(SELECTED));
    }

    @Test
    public void setReasonType() {
        final Reason reason = new Reason();
        reason.setReasonType(REASON_TYPE);
        assertThat(reason.getReasonType(), is(REASON_TYPE));
    }

    @Test
    public void setDetail() {
        final Reason reason = new Reason();
        reason.setDetail(DETAIL);
        assertThat(reason.getDetail(), is(DETAIL));

    }
}
