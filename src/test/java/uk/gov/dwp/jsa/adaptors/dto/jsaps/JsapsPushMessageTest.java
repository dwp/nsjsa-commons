package uk.gov.dwp.jsa.adaptors.dto.jsaps;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class JsapsPushMessageTest {

    private String code = "";
    private String message = "";
    private String screen = "";
    private String claimantId = "";
    private String toString = "E1234: Message on screen JA123456 for claimant ";

    @Before
    public  void setUp(){
        this.code = "E1234";
        this.message = "Message";
        this.screen = "JA123456";
        this.claimantId = UUID.randomUUID().toString();
    }

    @Test
    public void hasDefaultFieldValues() {
        final JsapsPushMessage pushMessage = new JsapsPushMessage();
        assertThat(pushMessage.getClaimantId(), is(nullValue()));
        assertThat(pushMessage.getCode(), is(nullValue()));
        assertThat(pushMessage.getMessage(), is(nullValue()));
        assertThat(pushMessage.getScreen(), is(nullValue()));
    }

    @Test
    public void testConstructorSetsValues(){
        JsapsPushMessage pushMessage = new JsapsPushMessage(code, message, screen, claimantId);
        assertThat(pushMessage.getClaimantId(), is(claimantId));
        assertThat(pushMessage.getCode(), is(code));
        assertThat(pushMessage.getMessage(), is(message));
        assertThat(pushMessage.getScreen(), is(screen));
    }

    @Test
    public void testToString(){
        JsapsPushMessage pushMessage = new JsapsPushMessage(code, message, screen, claimantId);
        assertThat(pushMessage.toString(), is(toString + claimantId));
    }

    @Test
    public void testSetCode() {
        JsapsPushMessage pushMessage = new JsapsPushMessage();
        pushMessage.setCode(code);
        assertEquals(pushMessage.getCode(), code);
    }

    @Test
    public void testSetClaimantId() {
        JsapsPushMessage pushMessage = new JsapsPushMessage();
        pushMessage.setClaimantId(claimantId);
        assertEquals(pushMessage.getClaimantId(), claimantId);
    }

    @Test
    public void testSetMessage() {
        JsapsPushMessage pushMessage = new JsapsPushMessage();
        pushMessage.setMessage(message);
        assertEquals(pushMessage.getMessage(), message);
    }

    @Test
    public void testSetScreen() {
        JsapsPushMessage pushMessage = new JsapsPushMessage();
        pushMessage.setScreen(screen);
        assertEquals(pushMessage.getScreen(), screen);
    }

}
