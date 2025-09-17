//import java.util.Scanner;
//
//public class Main {
//  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    TransactionFunctionality tf = new TransactionFunctionality();
//
//    // Add some sample transactions
//    tf.addTransaction(new Transactions("Coffee", "2025-08-20", 3.5, false));   // withdrawal
//    tf.addTransaction(new Transactions("Salary", "2025-08-21", 1200.0, true)); // deposit
//
//    System.out.println("Welcome to Transaction Manager!");
//
//    boolean running = true;
//    while (running) {
//      System.out.println("\nChoose an option:");
//      System.out.println("1. View all transactions");
//      System.out.println("2. Add a new transaction");
//      System.out.println("3. Update an existing transaction");
//      System.out.println("4. Replace an existing transaction");
//      System.out.println("5. Delete a transaction");
//      System.out.println("6. Exit");
//      System.out.println("7. Show net amount");
//
//
//      int choice = sc.nextInt();
//      sc.nextLine(); // consume newline
//
//      switch (choice) {
//        case 1: // view all
//          System.out.println("\nAll Transactions:");
//          for (Transactions tx : tf.getAllTransactions()) {
//            System.out.println(tx);
//          }
//          break;
//
//        case 2: // add
//          System.out.print("Enter Name: ");
//          String name = sc.nextLine();
//          System.out.print("Enter Date: ");
//          String date = sc.nextLine();
//          System.out.print("Enter Amount: ");
//          double amount = sc.nextDouble();
//          sc.nextLine();
//
//          System.out.print("Is this a deposit? (true/false): ");
//          boolean category = sc.nextBoolean();
//
//          tf.addTransaction(new Transactions(name, date, amount, category));
//          System.out.println("Transaction added successfully!");
//          break;
//
//        case 3: // update
//          System.out.print("Enter Transaction ID to update: ");
//          int id = sc.nextInt();
//          sc.nextLine();
//
//          System.out.println("Which field would you like to update?");
//          System.out.println("1. Name");
//          System.out.println("2. Date");
//          System.out.println("3. Amount");
//          System.out.println("4. Category");
//          int field = sc.nextInt();
//          sc.nextLine();
//
//          boolean updated = false;
//          switch (field) {
//            case 1:
//              System.out.print("Enter new Name: ");
//              updated = tf.changeName(id, sc.nextLine());
//              break;
//            case 2:
//              System.out.print("Enter new Date: ");
//              updated = tf.changeDate(id, sc.nextLine());
//              break;
//            case 3:
//              System.out.print("Enter new Amount: ");
//              updated = tf.changeAmount(id, sc.nextDouble());
//              break;
//            case 4:
//              System.out.print("Enter new Category (true=deposit, false=withdrawal): ");
//              updated = tf.changeCategory(id, sc.nextBoolean());
//              break;
//            default:
//              System.out.println("Invalid field choice!");
//          }
//          System.out.println(updated ? "Transaction updated!" : "Transaction not found.");
//          break;
//
//        case 4: // replace
//          System.out.print("Enter Transaction ID to replace: ");
//          int replaceId = sc.nextInt();
//          sc.nextLine();
//
//          System.out.print("Enter New Name: ");
//          String rName = sc.nextLine();
//          System.out.print("Enter New Date: ");
//          String rDate = sc.nextLine();
//          System.out.print("Enter New Amount: ");
//          double rAmount = sc.nextDouble();
//          sc.nextLine();
//          System.out.print("Is this a deposit? (true/false): ");
//          boolean rCategory = sc.nextBoolean();
//
//          Transactions newTx = new Transactions(rName, rDate, rAmount, rCategory);
//          newTx.setTID(replaceId); // keep same ID for replacement
//          boolean swapped = tf.swapTransaction(newTx);
//
//          System.out.println(swapped ? "Transaction replaced!" : "Transaction not found.");
//          break;
//
//        case 5: // delete
//          System.out.print("Enter Transaction ID to delete: ");
//          int delId = sc.nextInt();
//          boolean deleted = tf.deleteTransaction(delId);
//          System.out.println(deleted ? "Transaction deleted!" : "Transaction not found.");
//          break;
//
//        case 6: // exit
//          running = false;
//          System.out.println("Goodbye!");
//          break;
//
//        case 7: // Net amount
//          double netAmount = tf.getNetAmount();
//          System.out.println("Net amount: $" + netAmount);
//          break;
//
//        default:
//          System.out.println("Invalid choice!");
//      }
//    }
//    sc.close();
//  }
//}
