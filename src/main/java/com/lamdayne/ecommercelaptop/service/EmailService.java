package com.lamdayne.ecommercelaptop.service;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    @Data
    @Builder
    public static class Mail {
        @Builder.Default
        private String from = "WebShop <web-shop@gmail.com>";
        private String to, cc, bcc, subject, body;
        private MultipartFile file;
    }

    void send(Mail mail);

    default void send(String to, String subject, String body) {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        this.send(mail);
    }

    void push(Mail mail);

    default void push(String to, String subject, String body){
        this.push(Mail.builder().to(to).subject(subject).body(body).build());
    }
}
