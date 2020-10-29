package com.techelevator;

import com.techelevator.Item;

import java.math.BigDecimal;

public class Drinks extends Item {

    public Drinks(String name, BigDecimal price, String type) {
        super(name, price, type);
        sound = "Glug Glug, Yum!";
    }

}
