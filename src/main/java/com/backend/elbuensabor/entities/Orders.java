package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends GenericEntity{

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "total")
    private Double total;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "estimated_time")
    private Duration estimatedTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_delivery_type")
    private DeliveryType deliveryType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_payment_type")
    private PaymentType paymentType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_order_status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "fk_customer")
    private Customer customer;
}
