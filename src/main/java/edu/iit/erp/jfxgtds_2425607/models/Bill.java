package edu.iit.erp.jfxgtds_2425607.models;

public class Bill {
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
    public Bill(String billNumber, String itemCode, double internalPrice, double discountPrice, double salePrice, int quantity, double lineTotal, double grandTotal, int checksum) {
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

    // Getters (must match the names used in PropertyValueFactory)
    public String getBillNumber() { return billNumber; }
    public String getItemCode() { return itemCode; }
    public double getInternalPrice() { return internalPrice; }
    public double getDiscountPrice() { return discountPrice; }
    public double getSalePrice() { return salePrice; }
    public int getQuantity() { return quantity; }
    public double getLineTotal() { return lineTotal; }
    public double getGrandTotal() { return grandTotal; }
    public int getChecksum() { return checksum; }
}