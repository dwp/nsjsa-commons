package uk.gov.dwp.jsa.adaptors.dto.claim;

public class LanguagePreference {

    private Boolean welshSpeech;
    private Boolean welshContact;

    public LanguagePreference() { }

    public LanguagePreference(final Boolean welshSpeech, final Boolean welshContact) {
        this.welshSpeech = welshSpeech;
        this.welshContact = welshContact;
    }

    public Boolean getWelshSpeech() {
        return welshSpeech;
    }

    public void setWelshSpeech(final Boolean welshSpeech) {
        this.welshSpeech = welshSpeech;
    }

    public Boolean getWelshContact() {
        return welshContact;
    }

    public void setWelshContact(final Boolean welshContact) {
        this.welshContact = welshContact;
    }
}
