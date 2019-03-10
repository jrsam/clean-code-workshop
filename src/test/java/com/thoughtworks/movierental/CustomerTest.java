package com.thoughtworks.movierental;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CustomerTest {
    public static Rental rental1;
    public static Rental rental2;
    public static Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("Sam");
    }

    @Test
    public void testNewReleaseRentalAndBonusFrequentFlyer() {
        rental1 = new Rental(new Movie("Rapunzel", 2), 5);
        rental2 = new Rental(new Movie("Thor Rangnarok", 1), 1);

        customer.addRental(rental1);
        customer.addRental(rental2);

        String expectedStatement = "Rental Record for Sam\n"+
        "\t"+"Rapunzel\t4.5"+"\n"+
        "\t"+"Thor Rangnarok\t3.0"+"\n"+
        "Amount owed is 7.5\n" +
        "You earned 2 frequent renter points";

        Assert.assertEquals(expectedStatement, customer.statement());
    }

    @Test
    public void testNewReleaseRentalAndWithoutBonusFrequentFlyer() {
        rental1 = new Rental(new Movie("Rapunzel", 2), 5);
        rental2 = new Rental(new Movie("Thor Rangnarok", 1), 3);

        customer.addRental(rental1);
        customer.addRental(rental2);

        String expectedStatement = "Rental Record for Sam\n"+
                "\t"+"Rapunzel\t4.5"+"\n"+
                "\t"+"Thor Rangnarok\t9.0"+"\n"+
                "Amount owed is 13.5\n" +
                "You earned 3 frequent renter points";

        Assert.assertEquals(expectedStatement, customer.statement());
    }

    @Test
    public void testRegularRentalLessThanorEqualTo2Days() {
        rental1 = new Rental(new Movie("The Intern", 0), 1);
        customer.addRental(rental1);

        String expectedStatement = "Rental Record for Sam\n"+
                "\t"+"The Intern\t2.0"+"\n"+
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";

        Assert.assertEquals(expectedStatement, customer.statement());
    }

    @Test
    public void testRegularRentalMoreThan2Days() {
        rental1 = new Rental(new Movie("The Intern", 0), 4);
        customer.addRental(rental1);

        String expectedStatement = "Rental Record for Sam\n"+
                "\t"+"The Intern\t5.0"+"\n"+
                "Amount owed is 5.0\n" +
                "You earned 1 frequent renter points";

        Assert.assertEquals(expectedStatement, customer.statement());
    }
    @Test
    public void testChildrenRentalMoreThan3Days() {
        rental1 = new Rental(new Movie("Finding Nemo", 2), 4);
        customer.addRental(rental1);

        String expectedStatement = "Rental Record for Sam\n"+
                "\t"+"Finding Nemo\t3.0"+"\n"+
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";

        Assert.assertEquals(expectedStatement, customer.statement());
    }

    @Test
    public void testChildrenRentalLessThanOrEqualTo3Days() {
        rental1 = new Rental(new Movie("Finding Nemo", 2), 1);
        customer.addRental(rental1);

        String expectedStatement = "Rental Record for Sam\n"+
                "\t"+"Finding Nemo\t1.5"+"\n"+
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";

        Assert.assertEquals(expectedStatement, customer.statement());
    }


}