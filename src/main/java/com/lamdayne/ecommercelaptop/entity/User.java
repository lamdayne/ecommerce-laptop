package com.lamdayne.ecommercelaptop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private Date dob;
    private Boolean role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Cart> carts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Order> orders;
}
