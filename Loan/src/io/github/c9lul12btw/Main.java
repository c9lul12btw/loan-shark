package io.github.c9lul12btw;

import java.util.Date;
import java.util.Scanner;
import java.io.IOException;

public class Main {

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        userChoice();
    }

    public static void userChoice() {
        System.out.print("Would you like 'help', or to 'cont'?");
        String userEntryString = s.nextLine();

        switch(userEntryString.toLowerCase()) {
            case "help":
                help();
                break;
            case "cont":
                userEntryMain();
                break;
        }
    }

    public static void userEntryMain() {
        boolean allTestsPassed = false;

        System.out.print("Enter your full name: ");

        System.out.print("Enter your DoB (dd/mm/yyyy): ");

        System.out.print("Enter your address history (address/months stayed): ");

        System.out.print("Enter your current employment status (employed/unemployed/retired): ");

        System.out.print("Enter your annual income and monthly expenditure (income/expenditure): ");

        userEntryDob("22/07/2000");

        do {

        } while (allTestsPassed = false);
    }

    public static boolean userEntryName(String userFullName) {


        return true;
    }

    public static boolean userEntryDob(String userDob) {
        s = new Scanner(userDob).useDelimiter("/");
        int dateDay = Integer.parseInt(s.next());
        int dateMonth = Integer.parseInt(s.next());
        int dateYear = Integer.parseInt(s.next());

        Date currentDate = new Date();



        System.out.println(currentDate.toString());



        return true;
    }

    public static void help() {
        System.out.println("When prompted to enter a value, enter the value indicated.");
        System.out.println("Sometimes key entry phrases are described like so: ''entry''");
    }
}




