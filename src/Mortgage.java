import java.util.*;

// Overall this is a fairly simple approximation of a mortgage payment
// but the results are accurate enough for a cursory calculation
public class Mortgage {
    private static final int MAX_MORTGAGES = 10;
    private static int loans = 0;
    private static final Set<Mortgage> INVENTORY = new HashSet<>();

    private final int salePrice;
    private int down = 0;
    private final double interest;
    private final int period;
    private final int principal;
    private final double propertyTax;

    // Force static factory, prohibit subclassing
    private Mortgage() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter sale price (e.g. 300000): ");
        salePrice = s.nextInt();
        System.out.print("Enter down payment amount: ");
        int dn = s.nextInt();
        if (dn < 0)
            throw new IllegalArgumentException("Down payment cannot be negative!");
        else
            down = dn;
        principal = salePrice - down;
        System.out.print("Enter interest rate: ");
        interest = s.nextDouble();
        System.out.print("Enter mortgage period (in years): ");
        period = s.nextInt();
        System.out.print("Enter property tax rate: ");
        propertyTax = s.nextDouble();
        loans++;
    }

    public static Mortgage create() {
        if(loans < MAX_MORTGAGES)
            return new Mortgage();
        else if(INVENTORY.size() > 0) {
            Mortgage mg = INVENTORY.iterator().next();
            INVENTORY.remove(mg);
            return mg;
        } else
            throw new IllegalStateException("No mortgages available");
    }

    public static int getMaxMortgages()        { return MAX_MORTGAGES; }
    public static int getLoans()               { return loans;         }

    public static Set<Mortgage> getInventory() {
        // Return defensive copy
        return new HashSet<>(INVENTORY);
    }

    public int getSalePrice()      { return salePrice;   }
    public int getDownPayment()    { return down;        }
    public double getInterest()    { return interest;    }
    public int getPeriod()         { return period;      }
    public int getPrincipal()      { return principal;   }
    public double getPropertyTax() { return propertyTax; }

    private double monthlyInterest() {
        double result = interest / Utility.AS_PERCENT / Utility.MONTHS;
        return result;
    }

    private int getMonths() {
        if(period == 20)
            return Utility.MONTHS_20;
        else if(period == 30)
            return Utility.MONTHS_30;
        else
            return period * Utility.MONTHS;
    }

    private double getTaxes() {
        return propertyTax / Utility.AS_PERCENT *
                principal / Utility.MONTHS;
    }

    private double getMonthlyPayment() {
        double result = principal * ((monthlyInterest() * Math.pow(monthlyInterest() + 1, getMonths())) /
                (Math.pow(monthlyInterest() + 1, getMonths()) - 1)) + getTaxes();
        return result;
    }

    public void getAsCurrency() {
        double amount = getMonthlyPayment();
        System.out.format("%.2f", amount);
    }

    // Project can be expanded to include inventory of mortgages on hand
    // to distribute in event MAX_MORTGAGES is reached
    public static void addInventory(Mortgage mg) {
        INVENTORY.add(mg);
    }

    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Mortgage))
            return false;
        Mortgage mg = (Mortgage) o;
        return mg.salePrice == salePrice && mg.down == down &&
                mg.interest == interest && mg.propertyTax == propertyTax;
    }

    public int hashCode() {
        int result = Integer.hashCode(salePrice);
        result = 31 * result + Integer.hashCode(down);
        result = 31 * result + Double.hashCode(interest);
        result = 31 * result + Double.hashCode(propertyTax);
        return result;
    }

    // Method serves as main output of program
    public String toString() {
        return String.format("Sale price is $%,d and loan amount is $%,d." +
                "\nWith an interest rate of %.2f and property taxes of %.2f percent, " +
                "the monthly payments are $%,.2f.", salePrice, principal, interest,
                propertyTax, getMonthlyPayment());
    }
}
