package edu.iit.erp.jfxgtds_2425607.models;

public class Transaction {
    private String billNumber;
    private String itemCode;
    private double internalPrice;
    private double discountPrice;
    private double salePrice;
    private int quantity;
    private double lineTotal;
    private double grandTotal;
    private int checksum;

    // Constructor
    public Transaction(String billNumber, String itemCode, double internalPrice, double discountPrice, double salePrice, int quantity, double lineTotal, double grandTotal, int checksum) {
        this.billNumber = billNumber;
        this.itemCode = itemCode;
        this.internalPrice = internalPrice;
        this.discountPrice = discountPrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.lineTotal = lineTotal;
        this.grandTotal = grandTotal;
        this.checksum = checksum;
    }

    // Getters
    public String getBillNumber() { return billNumber; }
    public String getItemCode() { return itemCode; }
    public double getInternalPrice() { return internalPrice; }
    public double getDiscountPrice() { return discountPrice; }
    public double getSalePrice() { return salePrice; }
    public int getQuantity() { return quantity; }
    public double getLineTotal() { return lineTotal; }
    public double getGrandTotal() { return grandTotal; }
    public int getChecksum() { return checksum; }


    // Setters

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

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

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    //
    public Integer calculateProfit(Transaction transaction) {
        return (int) ((transaction.getInternalPrice() * transaction.getQuantity()) - (transaction.getSalePrice() * transaction.getQuantity() - transaction.getDiscountPrice()));

    }
}