import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== BankY CLI ===");
        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. List Accounts");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> createAccount();
                    case 2 -> deposit();
                    case 3 -> withdraw();
                    case 4 -> transfer();
                    case 5 -> checkBalance();
                    case 6 -> listAccounts();
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createAccount() throws Exception {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();
        System.out.print("Initial Deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());

        var acc = BankService.createAccount(name, pin, amount);
        System.out.println("Account created! Account Number: " + acc.getAccountNumber());
    }

    private static void deposit() throws Exception {
        System.out.print("Account Number: ");
        int accNum = Integer.parseInt(scanner.nextLine());
        System.out.print("Amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());

        BankService.deposit(accNum, amount);
        System.out.println("Deposit successful.");
    }

    private static void withdraw() throws Exception {
        System.out.print("Account Number: ");
        int accNum = Integer.parseInt(scanner.nextLine());
        System.out.print("Amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());

        BankService.withdraw(accNum, amount);
        System.out.println("Withdrawal successful.");
    }

    private static void transfer() throws Exception {
        System.out.print("From Account Number: ");
        int from = Integer.parseInt(scanner.nextLine());
        System.out.print("To Account Number: ");
        int to = Integer.parseInt(scanner.nextLine());
        System.out.print("Amount to transfer: ");
        double amount = Double.parseDouble(scanner.nextLine());

        BankService.transfer(from, to, amount);
        System.out.println("Transfer successful.");
    }

    private static void checkBalance() throws Exception {
        System.out.print("Account Number: ");
        int accNum = Integer.parseInt(scanner.nextLine());

        double balance = BankService.getBalance(accNum);
        System.out.println("Current balance: ₹" + balance);
    }

    private static void listAccounts() {
        var accounts = BankService.getAllAccounts();
        System.out.println("Account No. | Name               | Balance");
        System.out.println("------------------------------------------");
        for (var acc : accounts) {
            System.out.printf("%11d | %-18s | ₹%.2f%n",
                    acc.getAccountNumber(),
                    acc.getName(),
                    acc.getBalance());
        }
    }
}
