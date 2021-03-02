import java.util.*;

public class Mortgage {
    private static final int MAX_MORTGAGES = 10;
    private static int loans = 0;
    private static Set<Mortgage> inventory = new HashSet<>();

    private int salePrice;
    private int down = 0;
    private double interest;
    private int period;
    private int principal;
    private double propertyTax;

    private Mortgage() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter sale price (e.g. 300000): ");
        salePrice = s.nextInt();
        System.out.print("Enter down payment amount: ");
        down = s.nextInt();
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
        else if(inventory.size() > 0) {
            Mortgage mg = inventory.iterator().next();
            inventory.remove(mg);
            return mg;
        } else
            throw new IllegalStateException();
    }

    public static void addInventory(Mortgage mg) {
        inventory.add(mg);
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

    
}
