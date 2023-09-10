package com.backend.elbuensabor.DTO;

public class ItemSalesDTO {
    private Long itemId;
    private String itemName;
    private Long itemTypeId;
    private Long totalQuantitySold;

    public ItemSalesDTO(Long itemId, String itemName, Long itemTypeId, Long totalQuantitySold) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemTypeId = itemTypeId;
        this.totalQuantitySold = totalQuantitySold;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Long getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(Long totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
    }

}


