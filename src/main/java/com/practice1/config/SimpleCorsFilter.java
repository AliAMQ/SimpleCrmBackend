package com.practice1.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alireza on 2/7/2018.
 * Simple class to allow cross origin calls
 */

@Component
public class SimpleCorsFilter implements Filter {

    public SimpleCorsFilter(){
        super();
    }

    @Override
    public final void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = SimpleCorsFilter.addHeaders((HttpServletResponse) res);

        final HttpServletRequest request = (HttpServletRequest) req;

        /**
         * Alireza - to handle preflighted requests
         */
        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    public static HttpServletResponse addHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age","3600");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, WWW-Authenticate, Authorization, Origin, Content-Type, Version");
        response.setHeader("Access-Control-Expose-Headers", "X-Requested-With, WWW-Authenticate, Authorization, Origin, Content-Type");
        return response;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(final FilterConfig filterConfig){

    }

}
