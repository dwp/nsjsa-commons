package uk.gov.dwp.jsa.adaptors.dto.jsaps;

public class JsapsPushMessage {

    private String code = null;
    private String message = null;
    private String screen = null;
    private String claimantId = null;

    public JsapsPushMessage() {

    }

    public JsapsPushMessage(final String code, final String message, final String screen, final String claimantId) {
        this.code = code;
        this.message = message;
        this.screen = screen;
        this.claimantId = claimantId;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setClaimantId(final String claimantId) {
        this.claimantId = claimantId;
    }

    public void setScreen(final String screen) {
        this.screen = screen;
    }

    public String getScreen() {
        return screen;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getClaimantId() {
        return claimantId;
    }

    @Override
    public String toString() {
        return code
                + ": "
                + message
                + " on screen "
                + screen
                + " for claimant "
                + claimantId;
    }
}
