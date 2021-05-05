package main.java;

// Main program, run through command line
public class User {
    public static void main(String[] args) {
        Mortgage m = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
        m.monthlyInterest();
        m.getTaxes();
        //m.getMonthlyPayment();
    }
}
