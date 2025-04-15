package edu.iit.erp.jfxgtds_2425607.models;

public class Transaction {
    private String itemCode;
    private double internalPrice;
    private double discountPrice;
    private double salePrice;
    private int quantity;
    private int checksum;

    // Constructor
    public Transaction(String itemCode, double internalPrice, double discountPrice, double salePrice, int quantity, int checksum) {
        this.itemCode = itemCode;
        this.internalPrice = internalPrice;
        this.discountPrice = discountPrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.checksum = checksum;
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

    // Calculating profit
    public Integer calculateProfit(Transaction transaction) {
        return (int) ((transaction.getInternalPrice() * transaction.getQuantity()) - (transaction.getSalePrice() * transaction.getQuantity() - transaction.getDiscountPrice()));

    }
}