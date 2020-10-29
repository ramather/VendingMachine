package com.techelevator;

import com.techelevator.util.Log;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;



public class VendingMachine {
    private Map<String, Item> inventory = new LinkedHashMap<>();
    private BigDecimal balance = new BigDecimal(0);
    private BigDecimal amountCharged = new BigDecimal("0");

    public String getNameAndAmountSold(){
        String result = "";
        for(Map.Entry<String, Item> item : inventory.entrySet()) {
            result += item.getValue().getName()+ " | "+ item.getValue().getAmountSold() +"\n";
        }
        return result;
    }
    public BigDecimal getAmountCharged() {
        return amountCharged;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public String getNameOfItem(String slot){
        return inventory.get(slot).getName();
    }
    public BigDecimal getPriceOfItem(String slot){
        return inventory.get(slot).getPrice();
    }
    @Override
    public String toString() {
        return "VendingMachine{" +
                "balance=" + balance +
                '}';
    }

    public void displayInventory() {
        for (Map.Entry<String,Item> item : inventory.entrySet()){

            if(item.getValue().getQuantity()>0){
                System.out.println(item.getKey() +" " +item.getValue().getName() + " " + item.getValue().getPrice());
            }else
            System.out.println(item.getKey() + " SOLD OUT");




        }



    }

    public void loadInventory(String fileName){

        Path file = Paths.get(fileName);
        try (Scanner fileInput = new Scanner(file)){
            while(fileInput.hasNextLine()){
                String line = fileInput.nextLine();
                String[] data = line.split("\\|");
                String identifier = data[0];

                if(data[3].equals("Chip")){
                   Item item = new Chips(data[1], new BigDecimal((data[2])), data[3]);
                   inventory.put(identifier,item);
                }else if(data[3].equals("Candy")){
                   Item item = new Candy(data[1], new BigDecimal((data[2])), data[3]);
                   inventory.put(identifier,item);
                }else if (data[3].equals("Drink")){
                   Item item = new Drinks(data[1], new BigDecimal((data[2])), data[3]);
                   inventory.put(identifier,item);
                }else if (data[3].equals("Gum")){
                    Item item = new Gum(data[1], new BigDecimal((data[2])), data[3]);
                    inventory.put(identifier,item);
                }


            }
        } catch(IOException e){

        }

    }
    public String purchaseItem(String slot){
        inventory.get(slot).purchase();
        BigDecimal price = inventory.get(slot).getPrice();
        balance = balance.subtract(price);
        amountCharged = amountCharged.add(price);
        Soundable item = inventory.get(slot);
        return item.getSound();

    }
    public int getQuantityOfItem(String slot){
        return inventory.get(slot).getQuantity();
    }
    public void feedMoney(BigDecimal amount){
        balance = balance.add(amount);
        Log.logDeposit(getBalance(), amount);
    }
    public boolean containsSlot(String slot){
        return inventory.containsKey(slot);
    }
    public String getChange(){
        BigDecimal changeDue = balance;

        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.1");
        BigDecimal nickel = new BigDecimal("0.05");




        int numQuarters = 0;
        int numDimes = 0;
        int numNickels = 0;

        while (balance.compareTo(quarter) >= 0){
            balance = balance.subtract(quarter);
            numQuarters++;
        }
        while (balance.compareTo(dime) >= 0){
            balance = balance.subtract(dime);
            numDimes++;
        }
        while (balance.compareTo(nickel) >= 0){
            balance = balance.subtract(nickel);
            numNickels++;
        }


        return "Your change: " + numQuarters + " quarters " + numDimes +
                " dimes " +numNickels + " nickels ";
    }

    public Map<String, Item> getInventory() {
        return inventory;
    }
}
