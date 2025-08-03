import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            balance = 0;
        } else {
            balance = initialBalance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
}

public class ATM_Interface {
    private BankAccount account;
    private Scanner scanner;

    public ATM_Interface(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\nATM Main Menu");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: ₹%.2f\n", account.getBalance());
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter an amount greater than zero.");
            return;
        }

        if (account.withdraw(amount)) {
            System.out.printf("Withdrawal successful. New balance: ₹%.2f\n", account.getBalance());
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter an amount greater than zero.");
            return;
        }

        if (account.deposit(amount)) {
            System.out.printf("Deposit successful. New balance: ₹%.2f\n", account.getBalance());
        } else {
            System.out.println("Deposit failed.");
        }
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(0);
        ATM_Interface atm = new ATM_Interface(userAccount);
        atm.start();
    }
}
