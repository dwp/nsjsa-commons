package uk.gov.dwp.jsa.adaptors.http.api;

import org.junit.Test;

import java.net.URI;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ApiSuccessTest {

    public static final URI PATH = URI.create("http://www.dav.com");
    public static final Object DATA = new Object();

    @Test
    public void hasDefaultFieldValues() {
        final ApiSuccess apiSuccess = new ApiSuccess();
        assertThat(apiSuccess.getPath(), is(nullValue()));
        assertThat(apiSuccess.getData(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final ApiSuccess apiSuccess = new ApiSuccess(PATH, DATA);
        assertThat(apiSuccess.getPath(), is(PATH));
        assertThat(apiSuccess.getData(), is(DATA));
    }

    @Test
    public void setPath() {
        final ApiSuccess apiSuccess = new ApiSuccess();
        apiSuccess.setPath(PATH);
        assertThat(apiSuccess.getPath(), is(PATH));
    }

    @Test
    public void setData() {
        final ApiSuccess apiSuccess = new ApiSuccess();
        apiSuccess.setData(DATA);
        assertThat(apiSuccess.getData(), is(DATA));
    }
}
