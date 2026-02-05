package com.lamdayne.ecommercelaptop.interceptor;

import com.lamdayne.ecommercelaptop.constant.SessionConstant;
import com.lamdayne.ecommercelaptop.entity.User;
import com.lamdayne.ecommercelaptop.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final SessionUtil sessionUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler
    ) throws Exception {
        String uri = request.getRequestURI();
        sessionUtil.set(SessionConstant.SECURITY_URI, uri);
        User user = (User) sessionUtil.get(SessionConstant.SESSION_USER);
        if (user == null) { // not login
            response.sendRedirect("/auth/login");
            return false;
        }

        if (uri.startsWith("/admin") && !user.getRole()) { // not admin
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
