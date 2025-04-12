package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import edu.iit.erp.jfxgtds_2425607.utils.AppExceptions;
import edu.iit.erp.jfxgtds_2425607.utils.CSVUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileImportManager {

    public List<Transaction> getCSVDatatoArray(String fileName) throws AppExceptions.FileFormatErrorException, AppExceptions.FileNotFoundErrorException {
        List<Transaction> transactionsList = new ArrayList<>();
        File csvFile = new File(fileName);

        if (!csvFile.exists()) {
            throw new AppExceptions.FileNotFoundErrorException("File not found");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName));) {
            String line;
            while ((line = br.readLine()) != null) {

                if (!CSVUtil.formatChecker(line)) {
                    throw new AppExceptions.FileFormatErrorException("Invalid file format");
                }

                Transaction transaction = getBill(line);
                transactionsList.add(transaction);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return transactionsList;
    }

    private static Transaction getBill(String line) {
        String[] data = line.split(",");

        Transaction transaction = new Transaction(
                data[0],                        // Bill Number
                data[1],                        // Item code
                Double.parseDouble(data[2]),    // Internal Price
                Double.parseDouble(data[3]),    // Discount Price
                Double.parseDouble(data[4]),    // Sale Price
                Integer.parseInt(data[5]),      // Quantity
                Double.parseDouble(data[6]),    // Line Total
                Double.parseDouble(data[7]),    // Grand Total
                Integer.parseInt(data[8])       // Checksum
        );
        return transaction;
    }

}
