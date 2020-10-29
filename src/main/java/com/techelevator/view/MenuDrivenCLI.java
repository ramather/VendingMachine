package com.techelevator.view;

import java.util.Scanner;
//can add
public class MenuDrivenCLI implements BasicUI{

    private final Scanner userInput = new Scanner(System.in);
    private final Menu menu = new Menu(System.in, System.out);

    @Override
    public void output(String content) {
        System.out.println(); //Print blank line
        System.out.println(content);
    }

    @Override
    public void pauseOutput() {
        System.out.println("(Press enter to continue)");
        userInput.nextLine();
    }

    @Override
    public String promptForSelection(String[] options) {
        return (String) menu.getChoiceFromOptions(options);
    }
}
