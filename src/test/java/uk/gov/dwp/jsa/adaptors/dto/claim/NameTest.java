package uk.gov.dwp.jsa.adaptors.dto.claim;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class NameTest {

    public static final String TITLE = "TITLE";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";

    @Test
    public void hasDefaultFieldValues() {
        final Name name = new Name();
        assertThat(name.getTitle(), is(nullValue()));
        assertThat(name.getFirstName(), is(nullValue()));
        assertThat(name.getLastName(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final Name name = new Name(TITLE, FIRST_NAME, LAST_NAME);
        assertThat(name.getTitle(), is(TITLE));
        assertThat(name.getFirstName(), is(FIRST_NAME));
        assertThat(name.getLastName(), is(LAST_NAME));
    }

    @Test
    public void setTitle() {
        final Name name = new Name();
        name.setTitle(TITLE);
        assertThat(name.getTitle(), is(TITLE));
    }

    @Test
    public void setFirstName() {
        final Name name = new Name();
        name.setFirstName(FIRST_NAME);
        assertThat(name.getFirstName(), is(FIRST_NAME));
    }

    @Test
    public void setLastName() {
        final Name name = new Name();
        name.setLastName(LAST_NAME);
        assertThat(name.getLastName(), is(LAST_NAME));
    }
}
