package com.ngoctin.intuitionmobile.models;

public class Order {

    private Long id;
    private int userID;
    private int promotionId;
    private int addressId;
    private float orderPrice;

    public Order(Long id, int userID, int promotionId, int addressId, float orderPrice) {
        this.id = id;
        this.userID = userID;
        this.promotionId = promotionId;
        this.addressId = addressId;
        this.orderPrice = orderPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userID +
                ", promotionId=" + promotionId +
                ", addressId=" + addressId +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
