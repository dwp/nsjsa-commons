package uk.gov.dwp.jsa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);
    private static final String COOKIE_CLAIM_ID = "claim_id";

    @Override
    public boolean preHandle(
            final HttpServletRequest request, final HttpServletResponse response, final Object handler
    ) {
        if (handler instanceof HandlerMethod) {
            log("preHandle", request, (HandlerMethod) handler);
        }

        return true;
    }

    @Override
    public void postHandle(
            final HttpServletRequest request, final HttpServletResponse response,
            final Object handler, final ModelAndView modelAndView
    ) {
        if (handler instanceof HandlerMethod) {
            log("postHandle", request, (HandlerMethod) handler, response.getStatus());
        }
    }

    private void log(final String id, final HttpServletRequest request, final HandlerMethod handlerMethod) {
        LOG.info(logMessage(id, request, handlerMethod));
    }

    private void log(final String id, final HttpServletRequest request,
                     final HandlerMethod handlerMethod, final int httpStatusCode) {
        LOG.info(logMessage(id, request, handlerMethod) + "[" + httpStatusCode + "]");
    }

    private String logMessage(final String id, final HttpServletRequest request, final HandlerMethod handlerMethod) {
        String controllerName = handlerMethod.getBeanType().getName();
        Cookie claimIdCookie = WebUtils.getCookie(request, COOKIE_CLAIM_ID);
        String claimId = null;

        if (claimIdCookie != null) {
            claimId = claimIdCookie.getValue();
        }

        return String.format(
                "[%s][%s][%s][%s][%s]", id, controllerName, request.getRequestURI(), request.getMethod(), claimId
        );
    }
}
