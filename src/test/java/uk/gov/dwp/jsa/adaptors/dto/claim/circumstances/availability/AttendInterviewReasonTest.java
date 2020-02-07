package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.availability;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AttendInterviewReasonTest {

    @Test
    public void hasCorrectValues() {
        assertThat(AttendInterviewReason.values().length, is(7));
        assertThat(Arrays.asList(AttendInterviewReason.values()), hasItems(
                AttendInterviewReason.CARE_AND_RESPONSIBILITY,
                AttendInterviewReason.DRIVING_LESSON,
                AttendInterviewReason.I_AM_WORKING,
                AttendInterviewReason.JOB_INTERVIEW,
                AttendInterviewReason.JURY_SERVICE,
                AttendInterviewReason.MEDICAL_APPOINTMENT,
                AttendInterviewReason.OTHER));
    }


}
