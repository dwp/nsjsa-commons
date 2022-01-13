package uk.gov.dwp.jsa.config;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoggingInterceptorTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void beforeEachTest() {
        initMocks(this);
    }

    @Test
    public void preHandle() throws NoSuchMethodException {
        LoggingInterceptor sut = new LoggingInterceptor();
        when(request.getRequestURI()).thenReturn("request_uri");
        when(request.getMethod()).thenReturn("GET");

        Method method = TestController.class.getMethod("testMethod");
        TestController controller = new TestController();
        HandlerMethod handlerMethod = new HandlerMethod(controller, method);

        boolean result = sut.preHandle(request, response, handlerMethod);

        assertThat(result, is(true));
    }
}

class TestController {
    public void testMethod() {}
}
