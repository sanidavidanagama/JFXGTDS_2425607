package edu.iit.erp.jfxgtds_2425607.models;

public class Transaction {
    private String itemCode;
    private double internalPrice;
    private double discountPrice;
    private double salePrice;
    private int quantity;
    private int checksum;
    private double profit;

    // Constructor
    public Transaction(String itemCode, double internalPrice, double discountPrice, double salePrice, int quantity, int checksum) {
        this.itemCode = itemCode;
        this.internalPrice = internalPrice;
        this.discountPrice = discountPrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.checksum = checksum;
        this.profit = ((internalPrice * quantity) - (salePrice * quantity - discountPrice));
    }

    // Getters
    public String getItemCode() {
        return itemCode;
    }

    public double getInternalPrice() {
        return internalPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getChecksum() {
        return checksum;
    }

    public double getProfit() {
        return profit;
    }


    // Setters


    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setInternalPrice(double internalPrice) {
        this.internalPrice = internalPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}