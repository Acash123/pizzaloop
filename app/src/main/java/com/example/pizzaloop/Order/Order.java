package com.example.pizzaloop.Order;

public class Order {
    String orderId;
    String userId;
    String pizzaName;
    int qty;
    Double totalPrice;
    String paymentMethod;
    String phoneNumber;
    String Address;
    String OrderStatus;
    String pizzaId;


    public Order(String orderId, String userId, String pizzaName, int qty, Double totalPrice, String paymentMethod, String phoneNumber, String address, String orderStatus,String pizzaId) {
        this.orderId = orderId;
        this.userId = userId;
        this.pizzaName = pizzaName;
        this.qty = qty;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.phoneNumber = phoneNumber;
        Address = address;
        OrderStatus = orderStatus;
        this.pizzaId=pizzaId;
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.OrderStatus = orderStatus;
    }
}
