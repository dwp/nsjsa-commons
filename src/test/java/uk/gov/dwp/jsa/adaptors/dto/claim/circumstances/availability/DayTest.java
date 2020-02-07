package uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.availability;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class DayTest {

    private static final Reason AFTERNOON_REASON = new Reason();
    private static final Reason MORNING_REASON = new Reason();
    private static final LocalDate DATE = LocalDate.now();
    private static final LocalDate SATURDAY = LocalDate.of(2019, 5, 4);
    private static final LocalDate WEEKDAY = LocalDate.of(2019, 5, 6);
    private static final LocalDate SUNDAY = LocalDate.of(2019, 5, 5);

    @Test
    public void constructorSetsFields() {
        Day day = new Day(DATE, MORNING_REASON, AFTERNOON_REASON);
        assertThat(day.getAfternoon(), is(AFTERNOON_REASON));
        assertThat(day.getMorning(), is(MORNING_REASON));
        assertThat(day.getDate(), is(DATE));
    }

    @Test
    public void constructorhasDefaults() {
        Day day = new Day();
        assertThat(day.getAfternoon(), is(nullValue()));
        assertThat(day.getMorning(), is(nullValue()));
        assertThat(day.getDate(), is(nullValue()));
    }

    @Test
    public void setDate() {
        Day day = new Day();
        day.setDate(DATE);
        assertThat(day.getDate(), is(DATE));

    }

    @Test
    public void setMorning() {
        Day day = new Day();
        day.setMorning(MORNING_REASON);
        assertThat(day.getMorning(), is(MORNING_REASON));
    }

    @Test
    public void setAfternoon() {
        Day day = new Day();
        day.setAfternoon(AFTERNOON_REASON);
        assertThat(day.getAfternoon(), is(AFTERNOON_REASON));
    }

    @Test
    public void isWeekendDayReturnsTrueForSaturday() {
        assertThat(Day.isWeekendDay(SATURDAY), is(true));
    }

    @Test
    public void isWeekendDayReturnsTrueForSunday() {
        assertThat(Day.isWeekendDay(SUNDAY), is(true));
    }

    @Test
    public void isWeekendDayReturnsFalseForWeekday() {
        assertThat(Day.isWeekendDay(WEEKDAY), is(false));
    }
}