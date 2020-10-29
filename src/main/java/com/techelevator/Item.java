package com.techelevator;

import java.math.BigDecimal;

public abstract class Item implements Soundable {
    private String name;
    private BigDecimal price;
    private String type;
    private int quantity = 5;//5 is default
    protected String sound;
    private int amountSold = 0;

    public Item(String name, BigDecimal price, String type){
        this.name = name;
        this.price = price;
        this.type= type;

    }

    public int getAmountSold() {
        return amountSold;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public void purchase() {
        quantity--;
        amountSold++;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getSound() {
        return this.sound;
    }
}
