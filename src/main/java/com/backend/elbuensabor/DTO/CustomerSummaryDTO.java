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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(Double totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

}
