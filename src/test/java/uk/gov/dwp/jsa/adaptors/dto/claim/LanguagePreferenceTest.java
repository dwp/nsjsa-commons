package uk.gov.dwp.jsa.adaptors.dto.claim;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class LanguagePreferenceTest {
    LanguagePreference languagePreference;

    @Before
    public void setup() {
        languagePreference = new LanguagePreference();
    }

    @Test
    public void getWelshSpeech() {
        assertThat(languagePreference.getWelshSpeech(), is(nullValue()));
    }

    @Test
    public void setWelshSpeech() {
        languagePreference.setWelshSpeech(true);
        assertThat(languagePreference.getWelshSpeech(), is(true));
    }

    @Test
    public void getWelshContact() {
        assertThat(languagePreference.getWelshContact(), is(nullValue()));
    }

    @Test
    public void setWelshContact() {
        languagePreference.setWelshContact(true);
        assertThat(languagePreference.getWelshContact(), is(true));
    }

    @Test
    public void paramsConstructor() {
        LanguagePreference newLanguagePreference = new LanguagePreference(false, false);
        assertThat(newLanguagePreference.getWelshContact(), is(false));
        assertThat(newLanguagePreference.getWelshSpeech(), is(false));
    }
}
