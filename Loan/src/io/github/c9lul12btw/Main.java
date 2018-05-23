package io.github.c9lul12btw;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;

public class Main {

    private static Scanner s = new Scanner(System.in);                                                                  //Interface which allows for user input from console.
    private static final double fixedInterestRate = 5.9;                                                                //Interest rate will always be the same, justifying global/constant referencing.

    public static void main(String[] args) throws IOException, InterruptedException {
        userChoice();                                                                                                   //Calls userChoice function.

        /*  Calling dataCalculate function with dataEntry as a parameter passes the double Array, allowing multiple pieces of data to be injected from an external method.*/
        dataCalculate(dataEntry());
    }

    private static double[] dataEntry() {
        System.out.print("Enter the loan amount: ");
        double loanAmount = s.nextDouble();                                                                             //Data input for amount of money to loan
        System.out.print("Enter the time frame (years): ");
        double loanLength = s.nextInt();                                                                                //Data input for length of loan

        return new double[]{loanAmount, loanLength};                                                                    //Passes the inputted data in the form of a double array
    }

    private static void dataCalculate(double[] loanData) {
        double loanAmount = loanData[0];                                                                                //Transfers the loan amount from the parameter array into the new local variable.
        double loanLength = loanData[1] * 12;                                                                           //Transfers the loan length from the parameter array into the new local variable.

        System.out.format("\nLoan of £%.2f over %d months, at a fixed %.2f" + "%%.", loanAmount, (int)loanLength, fixedInterestRate);

        double interestPerPeriod = fixedInterestRate / (12 * 100);                                                      //Changes the fixed interest rate into a monthly interest rate, so calculations can be made.

        //A = P * ( (r * (Math.pow(1 + r, n))) / (Math.pow(1 + r, n) - 1) );
        double amountPerPeriod = loanAmount * ( (interestPerPeriod * (Math.pow(1 + interestPerPeriod, loanLength))) / (Math.pow(1 + interestPerPeriod, loanLength) - 1) );
        System.out.format("\nThis is £%.2f per month, with %f%% interest per month.\n", amountPerPeriod, interestPerPeriod);

        printSchedule();                                                                                                //Writes the table headings and sets up the formatting
        printScheduleLine((int)loanLength, loanAmount, interestPerPeriod, amountPerPeriod);                             //Fills out the table with the calculated data.
    }

    private static void printScheduleLine(int loanLength, double loanAmount, double interestPerPeriod, double amountPerPeriod) {
        double oldBalance = loanAmount;                                                                                 //Creates oldBalance double using loanAmount parameter to fill out data in the table.
        for (int month = 0; month < (loanLength); month++) {                                                            //Loops around for the length of the loan inputted.
            double interestPayment = oldBalance * interestPerPeriod;                                                    //Calculates interest per month paid.
            double principalPayment = amountPerPeriod - interestPayment;                                                //Calculates principal per month paid.
            double newBalance = oldBalance - principalPayment;                                                          //Adjusts balance for the principal payment.

            System.out.format("%-10d%-10.2f%-10.2f%-10.2f%-12.2f%-10.2f\n", month, oldBalance, amountPerPeriod, interestPayment, principalPayment, newBalance);
            oldBalance = newBalance;                                                                                    //Finalizes balance and advances the schedule.
        }
        summary(loanLength, loanAmount, amountPerPeriod);
    }

    private static void printSchedule() {
        System.out.println("\nAmortization Schedule");
        for (int i = 0; i < 61; i++) {                                                                                  //Creates a visual barrier to break the program up
            System.out.print("-");
        }
        System.out.format("\n%-10s%-10s%-10s%-10s%-12s%-10s","Month","Old","Monthly","Interest","Principal","New");     //Prints out formatted headings
        System.out.format("\n%-10s%-10s%-10s%-10s%-12s%-10s\n\n","Number","Balance","Payment","Paid","Paid","Balance"); //Prints out formatted headings
    }

    private static void summary(int loanLength, double loanAmount, double amountPerPeriod) {
        System.out.println("SUMMARY:");
        System.out.println("Loan amount: " + loanAmount);
        System.out.println("Length of loan: " + loanLength);
        System.out.println("Payment per month: " + amountPerPeriod);
        System.out.println("Fixed interest rate: " + fixedInterestRate);
    }

    private static void userChoice() {
        System.out.print("Would you like 'help', or to 'cont'?: ");
        String userEntryString = s.nextLine();                                                                          //User inputs their choice.
        switch(userEntryString.toLowerCase()) {                                                                         //Switches on the contents of the userEntryString variable when changed to lowercase.
            case "help":                                                                                                //When contents are 'help'.
                help();                                                                                                 //Calls help function.
                break;
            case "cont":                                                                                                //When contents are 'cont'.
                userEntryMain();                                                                                        //Calls userEntryMain function, continuing the program.
                break;
        }
    }

    private static void userEntryMain() {
        boolean allTestsPassed;
        do {
            String userFullName;
            String userDob;
            String citizenship;
            String employment;
            String incomeInfo;

            System.out.print("Enter your full name: ");
            userFullName = s.nextLine();                                                                                //Stores user input for later use in validateName function.
            System.out.print("Enter your DoB (dd/mm/yyyy): ");
            userDob = s.nextLine();                                                                                     //Stores user input for later use in validateDob function.
            System.out.print("Have you lived in the UK for at least 3 years? (Y/N): ");
            citizenship = s.nextLine();                                                                                 //Stores user input for immediate use in validateAddress function.
            validateAddress(citizenship);                                                                               //Calls validateAddress function with citizenship as a parameter.
            System.out.print("Enter your current employment status (employed/unemployed/retired): ");
            employment = s.nextLine();                                                                                  //Stores user input for later use in validateEmployment function.
            System.out.print("Enter your annual income and monthly expenditure (income/expenditure): ");
            incomeInfo = s.next();                                                                                      //Is not explicitly used for validation.

            /*  Sets allTestsPassed to true if all validations come back as true, otherwise is set to false.   */
            allTestsPassed = validateName(userFullName) && validateDob(userDob) && validateAddress(citizenship) && validateEmployment(employment);

        } while (!allTestsPassed);                                                                                      //While allTestsPassed is false, does not exit loop

    }

    private static boolean validateName(String userFullName) {
        if (userFullName.chars().allMatch(Character::isLetter)) {                                                       //Check to see if all characters entered were letters
            return true;
        } else {
            System.out.println("Invalid input.");
            return false;
        }
    }

    private static boolean validateDob(String userDob) {
        Date currentDateRaw = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = df.format(currentDateRaw);
        LocalDate currentDateFormatted = LocalDate.parse(currentDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate userDobFormatted = LocalDate.parse(userDob, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int userAge = Period.between(userDobFormatted, currentDateFormatted).getYears();

        return userAge >= 18;                                                                                           //Returns true if userAge is over or equal to 18, stating that validation has succeeded.
    }

    private static boolean validateAddress(String citizenship) {
        if (citizenship.toLowerCase().equals("y")) {
            System.out.println("We require your last 3 years of addresses, and how long you lived at each one (months).");
            for (int time = 0; time < 36;) {                                                                            //Loops around for the amount of months in 3 years.
                System.out.print("Enter the address: ");
                String address = s.nextLine();                                                                          //Data input.
                System.out.println("Time stayed (months): ");
                time = time + s.nextInt();                                                                              //Data input.
                System.out.println("You have " + (36 - time) + " months left unaccounted for." );                       //Notifies the user if they have any time left remaining to enter for.
            }
            return true;
        } else if (citizenship.toLowerCase().equals("n")){
            System.out.println("You are required to live in the UK for 3 years to apply for this loan.");
            return false;
        } else {
            System.out.println("Invalid input.");
            return false;
        }
    }

    private static boolean validateEmployment(String employmentStatus) {
        switch (employmentStatus) {                                                                                     //Switches on the value of employmentStatus inputted earlier.
            case "employed": {                                                                                          //If it equals "employed".
                return true;
            }
            case "unemployed": {                                                                                        //If it equals "unemployed".
                return false;
            }
            case "retired": {                                                                                           //If it equals "retired".
                return false;
            }
            default: {                                                                                                  //If it isn't a specific value.
                return false;
            }
        }
    }

    private static void help() {
        System.out.println("When prompted to enter a value, enter the value indicated.");
        System.out.println("Sometimes key entry phrases are described like so: ''entry''");
        System.out.println("When inputting data, follow the format suggested in the (parentheses).");
    }
}




