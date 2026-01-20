package com.lamdayne.ecommercelaptop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
public class Product {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private String name;

    @Column(name="base_price")
    private Double basePrice;

    @Column(name="sale_price")
    private Double salePrice;

    private long stock;
    private String thumbnail;
    private String description;
    private Integer status;

}
