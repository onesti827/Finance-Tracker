import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestCases {

    @Test
    void testAddTransaction() {
        TransactionFunctionality tf = new TransactionFunctionality();
        Transactions t1 = new Transactions("Salary", "2025-09-01", 2000.0, true);
        tf.addTransaction(t1);

        assertEquals(1, tf.getAllTransactions().size());
        assertEquals(t1, tf.getAllTransactions().get(0));
    }

    @Test
    void testDeleteTransaction() {
        TransactionFunctionality tf = new TransactionFunctionality();
        Transactions t1 = new Transactions("Coffee", "2025-09-02", 5.0, false);
        tf.addTransaction(t1);

        boolean deleted = tf.deleteTransaction(t1.getTID());
        assertTrue(deleted);
        assertEquals(0, tf.getAllTransactions().size());
    }

    @Test
    void testChangeName() {
        TransactionFunctionality tf = new TransactionFunctionality();
        Transactions t1 = new Transactions("Book", "2025-09-03", 12.0, false);
        tf.addTransaction(t1);

        boolean updated = tf.changeName(t1.getTID(), "Notebook");
        assertTrue(updated);
        assertEquals("Notebook", tf.getTransactions(t1.getTID()).getName());
    }

    @Test
    void testChangeAmount() {
        TransactionFunctionality tf = new TransactionFunctionality();
        Transactions t1 = new Transactions("Lunch", "2025-09-04", 10.0, false);
        tf.addTransaction(t1);

        boolean updated = tf.changeAmount(t1.getTID(), 15.0);
        assertTrue(updated);
        assertEquals(15.0, tf.getTransactions(t1.getTID()).getAmount());
    }

    @Test
    void testChangeCategory() {
        TransactionFunctionality tf = new TransactionFunctionality();
        Transactions t1 = new Transactions("Freelance", "2025-09-05", 500.0, true);
        tf.addTransaction(t1);

        boolean updated = tf.changeCategory(t1.getTID(), false);
        assertTrue(updated);
        assertFalse(tf.getTransactions(t1.getTID()).getCategory());
    }

    @Test
    void testSwapTransaction() {
        TransactionFunctionality tf = new TransactionFunctionality();
        Transactions t1 = new Transactions("Dinner", "2025-09-06", 30.0, false);
        tf.addTransaction(t1);

        Transactions t2 = new Transactions("Dinner Deluxe", "2025-09-06", 50.0, false);
        t2.setTID(t1.getTID());
        boolean swapped = tf.swapTransaction(t2);

        assertTrue(swapped);
        assertEquals("Dinner Deluxe", tf.getTransactions(t1.getTID()).getName());
        assertEquals(50.0, tf.getTransactions(t1.getTID()).getAmount());
    }

    @Test
    void testGetNetAmount() {
        TransactionFunctionality tf = new TransactionFunctionality();
        tf.addTransaction(new Transactions("Salary", "2025-09-01", 2000.0, true));
        tf.addTransaction(new Transactions("Groceries", "2025-09-02", 150.0, false));
        tf.addTransaction(new Transactions("Bonus", "2025-09-03", 500.0, true));

        double net = tf.getNetAmount(); // 2000 + 500 - 150 = 2350
        assertEquals(2350.0, net);
    }
}
