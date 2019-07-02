package com.example.pizzaloop;

public class item_details {
    String BackgroundURL ;
    String name;
    double price;
    String details;
    String id;

    public String getId() {
        return id;
    }
    public void setId(){
        this.id=id;
    }
    public item_details(String name, double price, String BackgroundURL, String details,String id) {
        this.BackgroundURL = BackgroundURL;
        this.name=name;
        this.price=price;
        this.details=details;
        this.id=id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getBackgroundURL() {
        return BackgroundURL;
    }

    public void setBackgroundURL(String backgroundURL) {
        BackgroundURL = backgroundURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}