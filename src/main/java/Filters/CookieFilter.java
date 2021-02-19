package Filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        if (cookies == null) {
            System.out.println("Cookie not find");
        } else {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + "======" + cookie.getValue() + "======" + cookie.getMaxAge());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);

//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
//        if (cookies.length == 0) {
//            response.sendRedirect("authorization.xhtml");
//        } else {
//            for (Cookie cookie : cookies) {
//                System.out.println(cookie.getName() + "======" + cookie.getValue() + "======" + cookie.getMaxAge());
//                if (cookie.getName().equals("authToken")) {
//                    System.out.println(cookie.getValue().length());
//                    System.out.println(cookie.getValue().isEmpty());
//                    if (!cookie.getValue().isEmpty()) {
//                        filterChain.doFilter(servletRequest, servletResponse);
//                    } else {
//                        response.sendRedirect("authorization.xhtml");
//                    }
//                }
//            }
//        }

    }


    @Override
    public void destroy() {
    }
}
