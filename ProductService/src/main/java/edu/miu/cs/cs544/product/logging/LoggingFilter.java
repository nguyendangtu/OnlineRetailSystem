package edu.miu.cs.cs544.product.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static edu.miu.cs.cs544.product.constants.CommonConstants.CORRELATION_ID;

/**
 * @author : JOHNNGUYEN
 * @since : 5/24/2023, Wed
 **/
@Slf4j
@Configuration
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        setCorrelationId(httpRequest);
        logRequest(httpRequest);
        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info(MDC.get(CORRELATION_ID) + ": completed");
        MDC.clear();
        Filter.super.destroy();
    }

    public void setCorrelationId(HttpServletRequest httpRequest) {
        MDC.put(CORRELATION_ID, httpRequest.getHeader(CORRELATION_ID));
    }

    public void logRequest(HttpServletRequest httpRequest) {
        log.info(MDC.get(CORRELATION_ID) + ": received request " + httpRequest.getMethod());
    }

}
