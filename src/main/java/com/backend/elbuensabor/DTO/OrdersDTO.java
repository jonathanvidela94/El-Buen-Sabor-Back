package com.backend.elbuensabor.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class OrdersDTO extends GenericDTO{
    private String address;
    private String apartment;
    private Double discount;
    private String estimatedTime;
    private LocalDateTime orderDate;
    private boolean paid;
    private String phone;
    private Double total;
    private Long customerId;
    private Long deliveryTypeId;
    private Long orderStatusId;
    private Long paymentTypeId;
    private List<OrderDetailDTO> orderDetails;
}
