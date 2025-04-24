package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.models.InvalidTransaction;
import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import edu.iit.erp.jfxgtds_2425607.utils.AppExceptions;
import edu.iit.erp.jfxgtds_2425607.utils.Checksum;

public class EditTransactionManager {

    private final Checksum checksum = new Checksum();

    public String validateItemCode(String itemCode) {
        if (containsSpecialCharacter(itemCode)) {
            return "Item code cannot contain special characters";
        }
        return "";
    }

    private boolean containsSpecialCharacter(String str) {
        return !str.matches("[a-zA-Z0-9]*");
    }


    public String validateItemPrice(String input) {
        try {
            double price = Double.parseDouble(input);
            if (price < 0) {
                return "Price can't be negative.";
            }
            return "";
        } catch (NumberFormatException e) {
            if (input.trim().startsWith("-")) {
                return "Enter a valid number. Negative values are not allowed.";
            } else {
                return "Enter a valid number.";
            }
        }
    }

    public String validateQuantity(String input) {
        try {
            int price = Integer.parseInt(input);
            if (price < 0) {
                return "Price can't be negative.";
            }
            return "";
        } catch (NumberFormatException e) {
            if (input.trim().startsWith("-")) {
                return "Enter a valid number. Negative values are not allowed.";
            } else {
                return "Enter a valid number.";
            }
        }
    }



    public Transaction getUpdateTransaction(String itemCode, Double internalPrice, Double discountPrice, Double salePrice, Integer quantity) {
        String editedRecord = String.format("%s%s%s%s%s", itemCode, internalPrice, discountPrice, salePrice, quantity);
        Integer checksumForRecord =  checksum.findChecksum(editedRecord);
        return new Transaction(itemCode, internalPrice, discountPrice, salePrice, quantity, checksumForRecord);
    }



}
