package com.lamdayne.ecommercelaptop.service.impl;

import com.lamdayne.ecommercelaptop.entity.User;
import com.lamdayne.ecommercelaptop.mapper.UserMapper;
import com.lamdayne.ecommercelaptop.service.AuthService;
import com.lamdayne.ecommercelaptop.service.EmailService;
import com.lamdayne.ecommercelaptop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${spring.mail.username}")
    private String from;
    private final UserService userService;
    private final EmailService emailService;
    private final Map<String, String> storedOtp = new ConcurrentHashMap<>();

    @Override
    public User login(String email, String password) {
        return userService.getUserByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public void forgotPassword(String email) {
        String otp = generateOtp();
        storedOtp.put(email, otp);
        EmailService.Mail mail = EmailService.Mail.builder()
                .from(from)
                .to(email)
                .subject("Forgot Password")
                .body("Mã otp là: " + otp)
                .build();
        emailService.send(mail);
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        if (!storedOtp.containsKey(email)) {
            return false;
        }

        String storedOtpValue = storedOtp.get(email);
        boolean isValid = otp.equals(storedOtpValue);
        if (isValid) {
            storedOtp.remove(email);
        }
        return isValid;
    }

    public String generateOtp() {
        // Tạo số ngẫu nhiên 6 chữ số từ 100000 đến 999999
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

}
