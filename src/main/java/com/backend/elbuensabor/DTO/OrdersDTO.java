package com.backend.elbuensabor.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class OrdersDTO extends GenericDTO{
    private String address;
    private String apartment;
    private Double discount;
    private Integer estimatedTime;
    private LocalDateTime orderDate;
    private boolean paid;
    private boolean cancelled;
    private String phone;
    private Double total;
    private Long customerId;
    private String customerName;
    private String customerLastname;
    private Long deliveryTypeId;
    private Long orderStatusId;
    private Long paymentTypeId;
    private List<OrderDetailDTO> orderDetails;
}
