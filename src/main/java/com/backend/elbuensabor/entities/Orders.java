package com.backend.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends GenericEntity{
    @Column(name = "order_number")
    private Long orderNumber;
    @Column(name = "date")
    private LocalDateTime date;
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
}
