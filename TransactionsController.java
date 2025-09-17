import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TransactionsController {

    @FXML private TextField nameField;
    @FXML private TextField amountField;
    @FXML private TextField dateField;
    @FXML private ChoiceBox<String> categoryBox;

    @FXML private Button addButton;
    @FXML private Button deleteButton;
    @FXML private Button clearAllButton;

    @FXML private TableView<Transactions> transactionTable;
    @FXML private TableColumn<Transactions, Integer> tidColumn;
    @FXML private TableColumn<Transactions, String> nameColumn;
    @FXML private TableColumn<Transactions, Double> amountColumn;
    @FXML private TableColumn<Transactions, String> dateColumn;
    @FXML private TableColumn<Transactions, String> categoryColumn;

    @FXML private Label netAmountLabel;

    private final TransactionFunctionality functionality = new TransactionFunctionality();
    private ObservableList<Transactions> observableTransactions;

    // For formatting dates from yyyy-MM-dd to MM/dd/yyyy
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @FXML
    public void initialize() {
        // ✅ Bind table columns to Transactions getters
        tidColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getTID()).asObject());
        nameColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        amountColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());

        // Format date safely
        dateColumn.setCellValueFactory(cellData -> {
            String rawDate = cellData.getValue().getDate();
            try {
                LocalDate date = LocalDate.parse(rawDate, inputFormatter);
                return new javafx.beans.property.SimpleStringProperty(date.format(outputFormatter));
            } catch (DateTimeParseException e) {
                return new javafx.beans.property.SimpleStringProperty(rawDate); // fallback
            }
        });

        // Boolean category → String
        categoryColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategory() ? "Deposit" : "Withdrawal"));

        // ✅ Link TableView to observable list
        observableTransactions = FXCollections.observableArrayList(functionality.getAllTransactions());
        transactionTable.setItems(observableTransactions);

        // Default category choices
        categoryBox.setItems(FXCollections.observableArrayList("Deposit", "Withdrawal"));
        categoryBox.setValue("Deposit");

        // Button actions
        addButton.setOnAction(e -> handleAddTransaction());
        deleteButton.setOnAction(e -> handleDeleteTransaction());
        clearAllButton.setOnAction(e -> handleClearAll());

        updateNetAmount();
    }

    // Add new transaction
    private void handleAddTransaction() {
        try {
            String name = nameField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String date = dateField.getText(); // keep as raw string (yyyy-MM-dd expected)
            boolean category = categoryBox.getValue().equals("Deposit");

            Transactions tx = new Transactions(name, date, amount, category);
            functionality.addTransaction(tx);
            observableTransactions.add(tx);

            updateNetAmount();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Amount must be a number.");
        }
    }

    // Delete selected row
    private void handleDeleteTransaction() {
        Transactions selected = transactionTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            functionality.deleteTransaction(selected.getTID());
            observableTransactions.remove(selected);
            updateNetAmount();

            // Reset IDs if table is empty
            if (observableTransactions.isEmpty()) {
                Transactions.resetTIDCounter();
            }
        } else {
            showAlert("No Selection", "Please select a transaction to delete.");
        }
    }

    // Clear all rows
    private void handleClearAll() {
        functionality.clearAllTransactions();
        observableTransactions.clear();
        updateNetAmount();
    }

    // Update net balance label
    private void updateNetAmount() {
        double net = functionality.getNetAmount();
        netAmountLabel.setText(String.format("Net Balance: $%.2f", net));
    }

    // Reset input fields
    private void clearFields() {
        nameField.clear();
        amountField.clear();
        dateField.clear();
        categoryBox.setValue("Deposit");
    }

    // Show error/warning alert
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
