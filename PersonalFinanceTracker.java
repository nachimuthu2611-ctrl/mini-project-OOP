import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    String type; // "Income" or "Expense"
    String description;
    double amount;

    public Transaction(String type, String description, double amount) {
        this.type = type;
        this.description = description;
        this.amount = amount;
    }

    public void display() {
        System.out.printf("%-10s | %-20s | %.2f\n", type, description, amount);
    }
}

public class PersonalFinanceTracker {
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Personal Finance Tracker =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. Show Summary");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            // Check input safety
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                sc.next(); 
                System.out.print("Enter your choice: ");
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addTransaction("Income", sc);
                    break;
                case 2:
                    addTransaction("Expense", sc);
                    break;
                case 3:
                    viewTransactions();
                    break;
                case 4:
                    showSummary();
                    break;
                case 5:
                    System.out.println("Thank you for using Personal Finance Tracker!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }

    private static void addTransaction(String type, Scanner sc) {
        System.out.print("Enter description: ");
        String desc = sc.nextLine();
        System.out.print("Enter amount: ");

        while (!sc.hasNextDouble()) {
            System.out.println("Invalid amount. Please enter a numeric value.");
            sc.next();
            System.out.print("Enter amount: ");
        }
        double amt = sc.nextDouble();
        sc.nextLine(); // consume newline

        transactions.add(new Transaction(type, desc, amt));
        System.out.println(type + " added successfully!");
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }
        System.out.println("\nType       | Description          | Amount");
        System.out.println("----------------------------------------------");
        for (Transaction t : transactions) {
            t.display();
        }
    }

    private static void showSummary() {
        double totalIncome = 0, totalExpense = 0;
        for (Transaction t : transactions) {
            if (t.type.equalsIgnoreCase("Income"))
                totalIncome += t.amount;
            else if (t.type.equalsIgnoreCase("Expense"))
                totalExpense += t.amount;
        }
        double balance = totalIncome - totalExpense;
        System.out.println("\n===== Summary =====");
        System.out.println("Total Income : " + totalIncome);
        System.out.println("Total Expense: " + totalExpense);
        System.out.println("Balance      : " + balance);
    }
}