import java.util.ArrayList;
import java.util.List;

public class TransactionFunctionality {

    // Array to store the transactions
    private List<Transactions> transactions = new ArrayList<>();

    // Add to the transactions ArrayList
    public void addTransaction(Transactions transaction) {
        transactions.add(transaction);
    }

    // Find the transaction by the TID
    public Transactions getTransactions(int id) {
        for (Transactions transaction : transactions) {
            if (transaction.getTID() == id) {
                return transaction; // return if found
            }
        }
        return null; // return null if not
    }

    // Function to delete a transaction with the help of TID
    public boolean deleteTransaction(int id) {
        return transactions.removeIf(tx -> tx.getTID() == id);
    }

    // Clear all transactions and reset TID
    public void clearAllTransactions() {
        transactions.clear();
        Transactions.resetTIDCounter(); // Reset static TID counter
    }

    // Change the TID
    public boolean changeTID(int oldTID, int newTID) {
        Transactions tx = getTransactions(oldTID);
        if (tx != null) {
            tx.setTID(newTID);
            return true;
        }
        return false;
    }

    // Change the name
    public boolean changeName(int id, String newName) {
        Transactions tx = getTransactions(id);
        if (tx != null) {
            tx.setName(newName);
            return true;
        }
        return false;
    }

    // Change the date
    public boolean changeDate(int id, String newDate) {
        Transactions tx = getTransactions(id);
        if (tx != null) {
            tx.setDate(newDate);
            return true;
        }
        return false;
    }

    // Change the amount
    public boolean changeAmount(int id, double newAmount) {
        Transactions tx = getTransactions(id);
        if (tx != null) {
            tx.setAmount(newAmount);
            return true;
        }
        return false;
    }

    // ✅ Change the category (now boolean, not String)
    public boolean changeCategory(int id, boolean newCategory) {
        Transactions tx = getTransactions(id);
        if (tx != null) {
            tx.setCategory(newCategory);
            return true;
        }
        return false;
    }

    // Swap the old transaction with the new one
    public boolean swapTransaction(Transactions tx) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getTID() == tx.getTID()) {
                transactions.set(i, tx); // replace the old transaction
                return true; // update successful
            }
        }
        return false; // not found
    }

    // Get all transactions
    public List<Transactions> getAllTransactions() {
        return transactions;
    }

    //Get the net amount
    public double getNetAmount() {
        double net = 0;
        for (Transactions tx : transactions) {
            if (tx.getCategory()) { // deposit
                net += tx.getAmount();
            } else { // withdrawal
                net -= tx.getAmount();
            }
        }
        return net;
    }

}
