import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Transactions {
    private String name;        // What is the transaction for (Eg: misc, food, shopping, clothes, etc.)
    private double amount;      // What amount is being dealt with
    private boolean category;   // true = deposit, false = withdrawal
    private String date;        // Date of the transaction
    private int TID;            // Transaction ID for tracking the transaction

    private static int nextTID = 1; // To assign unique IDs

    // Constructor
    public Transactions(String name, String date, double amount, boolean category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.TID = nextTID++; // assign then increment
    }

    // Getters
    public double getAmount() {
        return amount;
    }

    public boolean getCategory() {
        return category;
    }

    public int getTID() {
        return TID;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(boolean category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    // You probably don’t want to set TID manually, but I'll leave it here
    public void setTID(int TID) {
        this.TID = TID;
    }

    // Reset static TID counter (used when clearing all transactions)
    public static void resetTIDCounter() {
        nextTID = 1;
    }

    // Formatted date for display (MM/dd/yyyy)
    public String getFormattedDate() {
        LocalDate parsedDate = LocalDate.parse(date); // assumes date stored as yyyy-MM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return parsedDate.format(formatter);
    }

    @Override
    public String toString() {
        String type = category ? "Deposit" : "Withdrawal";
        return "TID: " + TID + " | Name: " + name + " | Amount: " + amount +
                " | Category: " + type + " | Date: " + getFormattedDate();
    }
}
