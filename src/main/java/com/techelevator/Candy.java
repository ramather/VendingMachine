package com.techelevator;

import com.techelevator.Item;

import java.math.BigDecimal;

public class Candy extends Item {

    public Candy(String name, BigDecimal price, String type) {
        super(name, price, type);
        sound = "Munch Munch, Yum!";
    }

}
