package edu.iit.erp.jfxgtds_2425607.models;

public class InvalidTransaction {

    private Transaction transaction;
    private String reason;

    public InvalidTransaction(Transaction transaction, String reason) {
        this.transaction = transaction;
        this.reason = reason;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
