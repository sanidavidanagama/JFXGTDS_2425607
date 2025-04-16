package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import edu.iit.erp.jfxgtds_2425607.utils.AppExceptions;
import edu.iit.erp.jfxgtds_2425607.utils.CSVUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileImportManager {

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Transaction> getCSVDatatoArray(String fileName) throws AppExceptions.FileFormatErrorException, AppExceptions.FileNotFoundErrorException {
        setFileName(fileName);
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
                Transaction transaction = getTransaction(line);
                transactionsList.add(transaction);
            }
            TransactionDataStore.getInstance().setTransactionList(transactionsList);
            File file = new File(fileName);
            TransactionDataStore.getInstance().setFileName(file.getName());
        } catch (IOException e) {
            System.out.println(e);
        }
        return transactionsList;
    }


    private static Transaction getTransaction(String line) {
        String[] data = line.split(",");

        Transaction transaction = new Transaction(
                data[0],                        // Item code
                Double.parseDouble(data[1]),    // Internal Price
                Double.parseDouble(data[2]),    // Discount Price
                Double.parseDouble(data[3]),    // Sale Price
                Integer.parseInt(data[4]),      // Quantity
                Integer.parseInt(data[5])       // Checksum
        );
        return transaction;
    }

}
