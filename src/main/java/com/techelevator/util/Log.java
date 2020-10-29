package com.techelevator.util;

import com.techelevator.VendingMachine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Log {
    public static void logEvent(String logText){
        try(FileOutputStream stream = new FileOutputStream("audit/file.log",true);
            PrintWriter writer = new PrintWriter(stream)) {
            writer.println(logText);
        } catch (Exception e){
            throw new LogException(e.getMessage());
        }
    }
    public static void logDeposit(BigDecimal balance, BigDecimal amount){
            logEvent(getDate() + " FEED MONEY: $" + amount  + " $" + balance);
    }
    public static void logPurchase(String name, String slot, BigDecimal price, BigDecimal balance){
       logEvent((getDate() + " "+ name +" "+ slot +" $"+ price + " $" + balance));

    }
    public static void logTransaction(BigDecimal change){
       logEvent(getDate() + " Give Change: " + "$" + change + " $0.00");
    }
    public static void salesReport(BigDecimal amountCharged, String salesInfo){
        try(FileOutputStream stream = new FileOutputStream("audit/SalesReport.log",false);
            PrintWriter writer = new PrintWriter(stream)) {
            writer.println("\n");
            writer.println(salesInfo);
            writer.println("** TOTAL SALES ** " + amountCharged);

        } catch (Exception e){
            throw new LogException(e.getMessage());
        }
    }
    public static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }
}
