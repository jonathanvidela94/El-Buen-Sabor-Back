package com.backend.elbuensabor.DTO;

public class CustomerSummaryDTO {
    private Long customerId;
    private String customerName;
    private String customerLastName;
    private Long orderCount;
    private Double totalOrderAmount;

    public CustomerSummaryDTO(Long customerId, String customerName, String customerLastName, Long orderCount, Double totalOrderAmount) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerLastName = customerLastName;
        this.orderCount = orderCount;
        this.totalOrderAmount = totalOrderAmount;
    }

}
