package io.github.c9lul12btw;

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


        int k = 0;
        while (k != 3) {
            System.out.print("Enter your full name: ");
            userEntryName = s.nextLine();

            System.out.print("Enter your date of birth: ");

            switch(k) {
                case 0:
                {

                }
                case 1:
                {
                    if (userEntryName.chars().allMatch(Character::isLetter)) {
                        k = 3;
                    }
                }
                case 2:
                {

                }
            }
        }
    }

    public static void help() {
        System.out.println("When prompted to enter a value, enter the value indicated.");
        System.out.println("Sometimes key entry phrases are described like so: ''entry''");
    }
}




