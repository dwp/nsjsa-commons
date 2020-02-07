package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class EducationTest {

    public static final String COURSE_NAME = "COURSE_NAME";
    public static final String INSTITUTION_NAME = "INSTITUTION_NAME";
    public static final LocalDate END_DATE = LocalDate.now();
    public static final LocalDate START_DATE = LocalDate.now();
    public static final int HOURS_PER_WEEK = 1;

    @Test
    public void constructorSetsFieldValues() {
        final Education education = new Education(
                COURSE_NAME, HOURS_PER_WEEK, START_DATE, END_DATE, INSTITUTION_NAME);
        assertThat(education.getCourseName(), is(COURSE_NAME));
        assertThat(education.getHoursPerWeek(), is(HOURS_PER_WEEK));
        assertThat(education.getStartDate(), is(START_DATE));
        assertThat(education.getEndDate(), is(END_DATE));
        assertThat(education.getInstitutionName(), is(INSTITUTION_NAME));

    }

    @Test
    public void hasDefaultFieldValues() {
        final Education education = new Education();
        assertThat(education.getCourseName(), is(nullValue()));
        assertThat(education.getHoursPerWeek(), is(nullValue()));
        assertThat(education.getStartDate(), is(nullValue()));
        assertThat(education.getEndDate(), is(nullValue()));
        assertThat(education.getInstitutionName(), is(nullValue()));
    }

    @Test
    public void setCourseName() {
        final Education education = new Education();
        education.setCourseName(COURSE_NAME);
        assertThat(education.getCourseName(), is(COURSE_NAME));
    }

    @Test
    public void setHoursPerWeek() {
        final Education education = new Education();
        education.setHoursPerWeek(HOURS_PER_WEEK);
        assertThat(education.getHoursPerWeek(), is(HOURS_PER_WEEK));
    }

    @Test
    public void setStartDate() {
        final Education education = new Education();
        education.setStartDate(START_DATE);
        assertThat(education.getStartDate(), is(START_DATE));
    }

    @Test
    public void setEndDate() {
        final Education education = new Education();
        education.setEndDate(END_DATE);
        assertThat(education.getEndDate(), is(END_DATE));
    }

    @Test
    public void setInstitutionName() {
        final Education education = new Education();
        education.setInstitutionName(INSTITUTION_NAME);
        assertThat(education.getInstitutionName(), is(INSTITUTION_NAME));
    }
}