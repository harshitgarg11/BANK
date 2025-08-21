import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private Map<String, Account> accounts;
    private Scanner scanner;

    public ATM() {
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in);
        // Pre-populate with a few sample accounts
        accounts.put("12345", new Account("12345", 1000.00));
        accounts.put("67890", new Account("67890", 500.50));
    }

    public void start() {
        System.out.println("Welcome to the Basic ATM Simulation 🏦");
        System.out.print("Please enter your account number: ");
        String accountNumber = scanner.nextLine();

        Account currentAccount = accounts.get(accountNumber);

        if (currentAccount == null) {
            System.out.println("Account not found. Exiting.");
            return;
        }

        System.out.println("Account found! What would you like to do?");
        showMenu(currentAccount);
    }

    private void showMenu(Account account) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.println("Your current balance is: $" + String.format("%.2f", account.getBalance()));
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}