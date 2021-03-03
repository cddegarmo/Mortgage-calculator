
import java.util.Scanner;
import java.text.NumberFormat;

public class MortgageCalculator {
    public static void main( String[] args ) {
        final byte monthsInYear = 12;
        final byte percentDivider = 100;

        Scanner sc = new Scanner( System.in );

        System.out.print("Principal: ");
        int principal = sc.nextInt();

        System.out.print("Annual interest rate: ");
        double interest = sc.nextDouble();
        double monthlyInterest = interest / percentDivider / monthsInYear;

        System.out.print("Payment period (years): ");
        int years = sc.nextInt();
        int months = years * monthsInYear;

        System.out.print("Property taxes (percentage): ");
        double taxes = sc.nextDouble();
        double monthlyTaxes = taxes / percentDivider * principal / monthsInYear;

        double mortgage = principal * ((monthlyInterest * Math.pow(monthlyInterest + 1, months)) /
                (Math.pow(monthlyInterest + 1, months) - 1)) + monthlyTaxes;

        String mortgageInDollars = NumberFormat.getCurrencyInstance().format( mortgage );
        System.out.println( "Monthly payment: " + mortgageInDollars );
    }
}
