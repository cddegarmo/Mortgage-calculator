package main.java;

// Main program, run through command line
public class User {
    public static void main(String[] args) {
        Mortgage m = Mortgage.create();
        System.out.println(m.toString());
    }
}
