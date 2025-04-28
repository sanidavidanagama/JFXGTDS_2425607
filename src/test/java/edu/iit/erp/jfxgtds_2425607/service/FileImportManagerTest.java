package edu.iit.erp.jfxgtds_2425607.service;

import edu.iit.erp.jfxgtds_2425607.model.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

public class FileImportManagerTest {

    private FileImportManager fileImportManager;
    private File tempFile;

    @Before
    public void setUp() {
        fileImportManager = new FileImportManager();
    }

    @After
    public void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testValidateImportedFile_FilePathEmpty() {
        fileImportManager.setAbsoluteFilePath("");
        fileImportManager.validateImportedFile();
        assertEquals("File path is empty. Please choose a file", fileImportManager.getStatusMessage());
    }

    @Test
    public void testValidateImportedFile_FileDoesNotExist() {
        fileImportManager.setAbsoluteFilePath("non_existing_file.csv");
        fileImportManager.validateImportedFile();
        assertEquals("File does not exist. Please verify the file path.", fileImportManager.getStatusMessage());
    }

    @Test
    public void testValidateImportedFile_WrongFileExtension() throws IOException {
        tempFile = File.createTempFile("testfile", ".txt");
        fileImportManager.setAbsoluteFilePath(tempFile.getAbsolutePath());
        fileImportManager.validateImportedFile();
        assertEquals("The file format appears to be incorrect. Please choose a .csv file", fileImportManager.getStatusMessage());
    }

    @Test
    public void testValidateImportedFile_CSVFormatError() throws IOException {
        tempFile = File.createTempFile("testfile", ".csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("invalid,line,without,enough,fields");  // Assuming CSVUtil expects exactly 6 columns.
        }
        fileImportManager.setAbsoluteFilePath(tempFile.getAbsolutePath());
        fileImportManager.validateImportedFile();
        assertNotEquals("File Imported Successfully.", fileImportManager.getStatusMessage());
    }

    @Test
    public void testValidateImportedFile_Success() throws IOException {
        tempFile = File.createTempFile("testfile", ".csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ITEM001,100.0,90.0,95.0,10,12345");
        }
        fileImportManager.setAbsoluteFilePath(tempFile.getAbsolutePath());
        fileImportManager.validateImportedFile();

        assertEquals("File Imported Successfully.", fileImportManager.getStatusMessage());
        assertEquals(tempFile.getName(), fileImportManager.getFileName());

        List<Transaction> transactions = fileImportManager.getTransactionsList();
        assertEquals(1, transactions.size());

        Transaction transaction = transactions.getFirst();
        assertEquals("ITEM001", transaction.getItemCode());
        assertEquals(100.0, transaction.getInternalPrice(), 0.001);
        assertEquals(90.0, transaction.getDiscountPrice(), 0.001);
        assertEquals(95.0, transaction.getSalePrice(), 0.001);
        assertEquals(10, transaction.getQuantity());
        assertEquals(12345, transaction.getChecksum());
    }

}
