package com.softtek.modelo;

public class OrderDetail {
    private int orderId;
    private int productId;
    private float unitPrice;
    private int quantity;
    private float discount;

    // Constructor
    public OrderDetail(int orderId, int productId, float unitPrice, int quantity, float discount) {
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public float getUnitPrice() {
        return unitPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getProductId() {
        return productId;
    }
    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}';
    }
}