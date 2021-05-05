package test.java;

import main.java.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MortgageTest {
   @Test
   public void testCreation() {
      Mortgage m = Mortgage.create(400000, 40000, 2.5, 1.8, 30);
      assertAll(
           () -> assertEquals(360000, m.getPrincipal()),
           () -> assertEquals(2.5, m.getInterest()),
           () -> assertEquals(1.8, m.getPropertyTax()),
           () -> assertEquals(30, m.getPeriod()));
   }

   @Test
   public void testInterest() {
      Mortgage m = Mortgage.create(400000, 40000, 2.5, 1.8, 30);
      assertEquals(0.002, m.monthlyInterest(), 0.0001);
   }

   @Test
   public void testMonths() {
      Mortgage m = Mortgage.create(400000, 40000, 2.5, 1.8, 18);
      Mortgage m20 = Mortgage.create(400000, 40000, 2.5, 1.8, 20);
      Mortgage m30 = Mortgage.create(400000, 40000, 2.5, 1.8, 30);
      assertAll(
           () -> assertEquals(216, m.getMonths()),
           () -> assertEquals(240, m20.getMonths()),
           () -> assertEquals(360, m30.getMonths()));
   }

   @Test
   public void testTaxes() {
      Mortgage m = Mortgage.create(400000, 40000, 2.5, 1.8, 30);
      assertEquals(540.0, m.getTaxes(), 0.001);
   }

   @Test
   public void testMonthly() {
      Mortgage m = Mortgage.create(400000, 40000, 2.5, 1.8, 30);
      assertEquals(1962.36, m.getMonthlyPayment(), 0.001);
   }

   @Test
   public void testMonthly2() {
      Mortgage m = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      assertEquals(5077.40, m.getMonthlyPayment(), 0.005);
   }

   @Test
   public void testConstructor() {
      Mortgage m = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      Mortgage m2 = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      Mortgage m3 = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      Mortgage m4 = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      Mortgage m5 = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      Mortgage m6 = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      Mortgage m7 = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      Mortgage m8 = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      Mortgage m9 = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      Mortgage m10 = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      assertThrows(IllegalStateException.class,
                   () -> Mortgage.create(700000, 10000,
                                         3.24, 2.03, 20));
   }

   @Test
   public void testOverrides() {
      Mortgage m = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      Mortgage m1 = Mortgage.create(700000, 10000, 3.24, 2.03, 20);
      assertAll(
           () -> assertTrue(m.equals(m1)),
           () -> assertTrue(m.hashCode() == m1.hashCode()));
   }
}