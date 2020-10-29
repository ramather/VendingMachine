package com.techelevator;

import com.techelevator.util.Log;
import com.techelevator.view.BasicUI;
import com.techelevator.view.MenuDrivenCLI;

import java.math.BigDecimal;
import java.util.Scanner;

public class Application {
//nothing is sacred and anything can be changed here
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
	private static final String EXIT = "Exit";
	private static final String DEPOSIT = "Feed money";
	private static final String SELECT = "Select Product";
	private static final String END_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {DEPOSIT, SELECT, END_TRANSACTION};
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, EXIT, MAIN_MENU_OPTION_SALES_REPORT};
	public boolean hasBeenPurchase = false;

	VendingMachine vm = new VendingMachine();
	private final BasicUI ui;

	public Application(BasicUI ui) {
		this.ui = ui;
	}

	public static void main(String[] args) {
		BasicUI cli = new MenuDrivenCLI();
		Application application = new Application(cli);
		application.run();
	}

	public void run() {

		vm.loadInventory("inventory.txt");
		boolean loop = true;
		while (loop) {
			String selection = ui.promptForSelection(MAIN_MENU_OPTIONS);

			if (selection.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vm.displayInventory();
				// display vending machine items
			} else if (selection.equals(MAIN_MENU_OPTION_PURCHASE)) {
				handlePurchaseMenu();
				// do purchase

			} else if (selection.equals(EXIT)){
				loop = false;
			} else if(selection.equals(MAIN_MENU_OPTION_SALES_REPORT)){
				if(!hasBeenPurchase){
					ui.output("Nothing has been purchased yet.");
				}else{
					ui.output(vm.getNameAndAmountSold());
				}
			}

		}
	}
	public void handlePurchaseMenu() {

		boolean loop = true;
		while (loop) {
			ui.output(vm.toString());

			String selection2 = ui.promptForSelection(PURCHASE_MENU_OPTIONS);


			if (selection2.equals(DEPOSIT)) {
				ui.output("Enter money");

				Scanner userInput = new Scanner(System.in);



				try{
					BigDecimal amount = new BigDecimal(userInput.nextLine());
					if(!amount.equals(BigDecimal.ZERO) && amount.compareTo(BigDecimal.ZERO)>0
							&& amount.remainder(new BigDecimal("1")).equals(BigDecimal.ZERO)){
						vm.feedMoney(amount);

					}else{
						ui.output("Please try again.");
					}

				} catch(NumberFormatException e){

				}


			} else if (selection2.equals(SELECT) && !vm.getBalance().equals(BigDecimal.ZERO)){
				ui.output("enter slot number");
				Scanner userInput = new Scanner(System.in);
				String slot = userInput.nextLine().toUpperCase();
				if( vm.containsSlot(slot) && vm.getQuantityOfItem(slot) > 0 &&
						vm.getPriceOfItem(slot).compareTo(vm.getBalance()) <0){
					String sound = vm.purchaseItem(slot);
					ui.output(sound);
					Log.logPurchase(vm.getNameOfItem(slot), slot, vm.getPriceOfItem(slot), vm.getBalance());
					hasBeenPurchase = true;
				}else{
					ui.output("Error Item unavailable or insufficient funds. Please choose again.");
				}



			}else if (selection2.equals(END_TRANSACTION)){
				Log.logTransaction(vm.getBalance());
				Log.salesReport(vm.getAmountCharged(), vm.getNameAndAmountSold());
				ui.output(vm.getChange());


				loop = false;
			}else if (vm.getBalance().equals(BigDecimal.ZERO)){
				ui.output("Please deposit money before making a selection.");
			}

		}
	}
}
