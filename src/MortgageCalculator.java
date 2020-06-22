import java.util.Scanner;
import java.text.NumberFormat;

public class MortgageCalculator {

    public static void main( String[] args ) {
        final byte monthsInYear = 12;
        final byte percentDivider = 100;

        Scanner sc = new Scanner( System.in );

        // Set principal of loan
        System.out.print( "Principal: " );
        int principal = sc.nextInt();

        // Convert annual interest rate into monthly interest rate
        System.out.print( "Annual interest rate: " );
        double interest = sc.nextDouble();
        double monthlyInterest = interest / percentDivider / monthsInYear;

        // Convert payment period into months
        System.out.print( "Payment period (years): " );
        int years = sc.nextInt();
        int months = years * monthsInYear;

        // Calculate property taxes (applicable to states with such taxes)
        System.out.print( "Property taxes (percentage): ");
        double taxes = sc.nextDouble();
        double monthlyTaxes = taxes / percentDivider * principal / monthsInYear;

        // Calculate mortgage plus monthly property taxes
        double mortgage = principal * ( ( monthlyInterest * Math.pow( monthlyInterest + 1, months ) ) /
                ( Math.pow( monthlyInterest + 1, months ) - 1 ) ) + monthlyTaxes;

        // Format value to U.S. dollar (USD) and print to console
        String mortgageInDollars = NumberFormat.getCurrencyInstance().format( mortgage );
        System.out.println( "Monthly payment: " + mortgageInDollars );
    }
}
