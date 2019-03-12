package com.thoughtworks.movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {
  private String name;
  private List<Rental> rentals = new ArrayList<>();

  private int frequentRenterPoints = 0;
  private double totalAmount = 0;


  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental arg) {
    rentals.add(arg);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    String result = "Rental Record for " + getName() + "\n";
    for (Rental each : rentals) {
      //show figures for this rental
      result += "\t" + each.getMovie().getTitle() + "\t" +
          String.valueOf(each.amount()) + "\n";
    }

    //add footer lines result
    result += "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
    result += "You earned " + String.valueOf(frequentRenterPoints)
        + " frequent renter points";
    return result;
  }

  private double getTotalAmount() {
    for (Rental each : rentals) {
      frequentRenterPoints = getFrequentRenterPoints(each);
      totalAmount += each.amount();
    }
    return totalAmount;
  }

  private int getFrequentRenterPoints(Rental each) {
    frequentRenterPoints++;
    // add bonus for a two day new release rental
    if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
        &&
        each.getDaysRented() > 1) frequentRenterPoints++;
    return frequentRenterPoints;
  }

}

