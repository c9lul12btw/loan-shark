package io.github.c9lul12btw;

import java.util.Scanner;

public class Old {

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Amortization example calculator.");
        paymentPerPeriod(entry());
    }

    private static double[] entry() {
        System.out.print("Enter the loan amount: ");
        double loanAmt = s.nextDouble();

        System.out.print("Enter the time frame (years): ");
        double timeFr = s.nextInt();

        System.out.print("Enter the interest rate: ");
        double interestRt = s.nextDouble();

        return new double[]{loanAmt, timeFr, interestRt};
    }

    private static int paymentPerPeriod(double[] data) {
        double loanAmt = data[0];
        double timeFr = data[1] * 12;
        double interestRt = data[2];
        double amountPerPeriod;

        System.out.format("\nLoan of £%.2f over %d months, at a fixed %.2f" + "%%.", loanAmt, (int)timeFr, interestRt);

        interestRt = data[2] / (12 * 100);

        //A = P * ( (r * (Math.pow(1 + r, n))) / (Math.pow(1 + r, n) - 1) );
        amountPerPeriod = loanAmt * ( (interestRt * (Math.pow(1 + interestRt, timeFr))) / (Math.pow(1 + interestRt, timeFr) - 1) );

        System.out.format("\nThis is £%.2f per month, with %f%% interest per month.\n", amountPerPeriod, interestRt);

        printTbl();
        printTblLine((int)timeFr, loanAmt, interestRt, amountPerPeriod);

        return 0;
    }

    private static int printTblLine(int timeFr, double loanAmt, double interestRt, double amountPerPeriod) {
        double oldBal = loanAmt;
        for (int i = 0; i < (timeFr); i++) {
            int month = i;
            double intPay = oldBal * interestRt;
            double priPay = amountPerPeriod - intPay;
            double newBal = oldBal - priPay;
            System.out.format("%-10d%-10.2f%-10.2f%-10.2f%-12.2f%-10.2f\n",month,oldBal,amountPerPeriod,intPay,priPay,newBal);
            oldBal = newBal;
        }
        return 0;
    }

    private static void printTbl() {
        System.out.println("\nAmortization Schedule");
        for (int i = 0; i < 61; i++) {
            System.out.print("-");
        }
        System.out.format("\n%-10s%-10s%-10s%-10s%-12s%-10s","Month","Old","Monthly","Interest","Principal","New");
        System.out.format("\n%-10s%-10s%-10s%-10s%-12s%-10s\n\n","Number","Balance","Payment","Paid","Paid","Balance");
    }
}
