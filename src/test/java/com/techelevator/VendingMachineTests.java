package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineTests {

    @Test
    public void money_is_fed(){
        VendingMachine sut = new VendingMachine();
        BigDecimal expectedBalance = new BigDecimal(10);
        sut.feedMoney(new BigDecimal(10));
        BigDecimal result = sut.getBalance();
        Assert.assertEquals(expectedBalance, result);
    }
    @Test
    public void chips_returns_crunch_crunch_yum(){
        VendingMachine sut = new VendingMachine();
        sut.loadInventory("inventory.txt");
        String expected = "Crunch Crunch, Yum!";

        String result = sut.purchaseItem("A1");
        Assert.assertEquals(expected, result);

    }
    @Test
    public void gum_returns_chew_chew(){
        VendingMachine sut = new VendingMachine();
        sut.loadInventory("inventory.txt");
        String expected = "Chew Chew, Yum!";

        String result = sut.purchaseItem("D2");
        Assert.assertEquals(expected, result);
    }
    @Test
    public void candy_returns_Munch(){
        VendingMachine sut = new VendingMachine();
        sut.loadInventory("inventory.txt");
        String expected = "Munch Munch, Yum!";

        String result = sut.purchaseItem("B3");
        Assert.assertEquals(expected, result);
    }
    @Test
    public void drink_returns_glug(){
        VendingMachine sut = new VendingMachine();
        sut.loadInventory("inventory.txt");
        String expected = "Glug Glug, Yum!";

        String result = sut.purchaseItem("C1");
        Assert.assertEquals(expected, result);
    }
    @Test
    public void item_was_purchased(){
        VendingMachine sut = new VendingMachine();
        sut.loadInventory("inventory.txt");
        sut.purchaseItem("A1");
        Map<String, Item> inventory = sut.getInventory();
        int result = 0;
        for (Map.Entry<String,Item> item : inventory.entrySet()){
            if(item.getKey().equals("A1")){
                result = item.getValue().getQuantity();
            }
        }
        Assert.assertEquals(4,result);
    }

    @Test
    public void one_dollar_returns_4_quarters_change(){
        VendingMachine sut = new VendingMachine();
        sut.loadInventory("inventory.txt");
        sut.feedMoney(new BigDecimal(1));
        String result = sut.getChange();
        String expected = "Your change: 4 quarters 0 dimes 0 nickels ";

        Assert.assertEquals(expected, result);
    }

}
