package edu.iit.erp.jfxgtds_2425607.controllers;

import edu.iit.erp.jfxgtds_2425607.models.InvalidTransaction;
import edu.iit.erp.jfxgtds_2425607.models.Transaction;
import edu.iit.erp.jfxgtds_2425607.service.EditTransactionManager;
import edu.iit.erp.jfxgtds_2425607.service.TransactionDataStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.awt.event.KeyEvent;

public class EditTransactionsController {

    @FXML
    private Label discountPriceErrorLabel;

    @FXML
    private TextField discountPriceInput;

    @FXML
    private Label internalPriceErrorLabel;

    @FXML
    private TextField internalPriceInput;

    @FXML
    private Label itemCodeErrorLabel;

    @FXML
    private TextField itemCodeInput;

    @FXML
    private Label quantityErrorLabel;

    @FXML
    private TextField quantityInput;

    @FXML
    private Label reasonLabel;

    @FXML
    private Label salePriceErrorLabel;

    @FXML
    private TextField salePriceInput;

    private final EditTransactionManager manager = new EditTransactionManager();


    private InvalidTransaction invalidTransaction;

    public InvalidTransaction getInvalidTransaction() {
        return invalidTransaction;
    }


    public void setInvalidTransaction(InvalidTransaction invalidTransaction) {
        this.invalidTransaction = invalidTransaction;
        setupLabelsAndPlaceholders();
    }

    private void setupInputFieldsWithPastData() {
        InvalidTransaction transaction = getInvalidTransaction();
        itemCodeInput.setText(transaction.getTransaction().getItemCode());
        internalPriceInput.setText(String.valueOf(transaction.getTransaction().getInternalPrice()));
        discountPriceInput.setText(String.valueOf(transaction.getTransaction().getDiscountPrice()));
        salePriceInput.setText(String.valueOf(transaction.getTransaction().getSalePrice()));
        quantityInput.setText(String.valueOf(transaction.getTransaction().getQuantity()));
    }

    private void setUpReasonForError() {
        reasonLabel.setText(invalidTransaction.getReason());
    }

    private void setupPlaceHoldersWithPastData() {
        InvalidTransaction transaction = getInvalidTransaction();
        itemCodeInput.setPromptText(transaction.getTransaction().getItemCode());
        internalPriceInput.setPromptText(String.valueOf(transaction.getTransaction().getInternalPrice()));
        discountPriceInput.setPromptText(String.valueOf(transaction.getTransaction().getDiscountPrice()));
        salePriceInput.setPromptText(String.valueOf(transaction.getTransaction().getSalePrice()));
        quantityInput.setPromptText(String.valueOf(transaction.getTransaction().getQuantity()));
    }

    private void setupLabelsAndPlaceholders() {
        setupInputFieldsWithPastData();
        setupPlaceHoldersWithPastData();
        setUpReasonForError();
    }

    private void validateAllFields() {
        itemCodeErrorLabel.setText(manager.validateItemCode(itemCodeInput.getText()));
        internalPriceErrorLabel.setText(manager.validateItemPrice(internalPriceInput.getText()));
        discountPriceErrorLabel.setText(manager.validateItemPrice(discountPriceInput.getText()));
        salePriceErrorLabel.setText(manager.validateItemPrice(salePriceInput.getText()));
        quantityErrorLabel.setText(manager.validateQuantity(quantityInput.getText()));
    }

    private boolean checkValidity() {
        String itemCode = itemCodeErrorLabel.getText();
        String internalPrice = internalPriceErrorLabel.getText();
        String discountPrice = discountPriceErrorLabel.getText();
        String salePrice = salePriceErrorLabel.getText();
        String quantity = quantityErrorLabel.getText();
        return itemCode.isEmpty() && internalPrice.isEmpty() && discountPrice.isEmpty() && salePrice.isEmpty() && quantity.isEmpty();
    }

    private Transaction createNewTransaction() {
        return manager.getUpdateTransaction(
                itemCodeInput.getText(),
                Double.valueOf(internalPriceInput.getText()),
                Double.valueOf(discountPriceInput.getText()),
                Double.valueOf(salePriceInput.getText()),
                Integer.valueOf(quantityInput.getText())
        );
    }

    private void save() {
        validateAllFields();
        if (checkValidity()) {
            Transaction editedTransaction = createNewTransaction();
            int indexOfInvalidTransaction =  TransactionDataStore.getInstance().findTransactionIndex(invalidTransaction.getTransaction());
            TransactionDataStore.getInstance().updateTransaction(indexOfInvalidTransaction, editedTransaction);
            Stage stage = (Stage) itemCodeInput.getScene().getWindow();
            stage.close();
        }

    }
    private void close() {
        Stage stage = (Stage) itemCodeInput.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        close();

    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {
        save();
    }

    @FXML
    void onEnterKeyPressed(javafx.scene.input.KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            save();
        }
        if (event.getCode() == KeyCode.ESCAPE) {
            close();
        }
    }
}
