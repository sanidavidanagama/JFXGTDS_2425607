package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import edu.iit.erp.jfxgtds_2425607.utils.CSVUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileImportManager {

    private String absoluteFilePath = "";

    private String fileName = "";

    private String statusMessage = "Please import a file with .csv format";

    private List<Transaction> transactionsList = new ArrayList<>();

    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }

    public void setAbsoluteFilePath(String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public void validateImportedFile() {
        if (getAbsoluteFilePath().isEmpty()) {
            setStatusMessage("File path is empty. Please choose a file");
            return;
        }
        File file = new File(getAbsoluteFilePath());
        if (!file.exists()) {
            setStatusMessage("File does not exist. Please verify the file path.");
            return;
        }
        if (!file.getName().endsWith(".csv")) {
            setStatusMessage("The file format appears to be incorrect. Please choose a .csv file");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String status = CSVUtil.formatChecker(line);
                if (!status.isEmpty()) {
                    setStatusMessage(status);
                    return;
                }
                Transaction transaction = getTransaction(line);
                transactionsList.add(transaction);
            }
        } catch (IOException e) {
            setStatusMessage("Failed to read file. Please try again.");
            return;
        }
        setFileName(file.getName());
        storeData(getTransactionsList(), getFileName());
        setStatusMessage("File Imported Successfully.");
    }


    private void storeData(List<Transaction> transactionList, String fileName) {
        TransactionDataStore.getInstance().setTransactionList(transactionList);
        TransactionDataStore.getInstance().setFileName(fileName);
    }


    private Transaction getTransaction(String line) {
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

    public void loadData() {
        setAbsoluteFilePath(TransactionDataStore.getInstance().getAbsoluteFilePath());
        setFileName(TransactionDataStore.getInstance().getFileName());
        setTransactionsList(TransactionDataStore.getInstance().getTransactionList());
        if (getAbsoluteFilePath().isEmpty()) {
            setStatusMessage("Please import a file with .csv format");

        }
        else {
            setStatusMessage("File Imported Successfully.");
        }
    }

}
