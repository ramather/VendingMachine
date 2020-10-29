package com.techelevator;

import com.techelevator.Item;

import java.math.BigDecimal;

public class Gum extends Item {

    public Gum(String name, BigDecimal price, String type) {
        super(name, price, type);
        sound = "Chew Chew, Yum!";
    }

}
