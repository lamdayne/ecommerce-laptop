package com.lamdayne.ecommercelaptop.util;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionUtil {

    private final HttpSession session;

    public Object get(String name) {
        return session.getAttribute(name);
    }

    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    public void remove(String name) {
        session.removeAttribute(name);
    }

}
