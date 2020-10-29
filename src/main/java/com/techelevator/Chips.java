package com.techelevator;

import com.techelevator.Item;

import java.math.BigDecimal;

public class Chips extends Item{
    public Chips(String name, BigDecimal price, String type){
        super(name, price, type);
        sound = "Crunch Crunch, Yum!";
    }

}
