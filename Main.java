import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        int choice;

        do{
            System.out.println("\n====BANK MANAGEMENT SYSTEM====");
            System.out.println("1.Create New Account");
            System.out.println("2.Login to Account");
            System.out.println("3.Display All Accounts(Admin)");
            System.out.println("4.Exit");

            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    loginAccount();
                    break;

                case 3:
                    bank.displayAllAccounts();
                    break;

                case 4:
                    System.out.println("Thank you for using our banking System. ");
                    break;

                case 5:
                    System.out.println("Invalid choice.Try again.");
            }
        } while(choice!=4);

        sc.close();
    }

    private static void createAccount() {

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Create 4-digit PIN: ");
        String pin = sc.nextLine();

        System.out.print("Enter Initial Deposit: Rs. ");
        BigDecimal amount = sc.nextBigDecimal();
        sc.nextLine();

        bank.createAccount(name,pin,amount);
    }

    private static void loginAccount() {

        System.out.print("Enter Account Number: ");
        String accountNumber = sc.nextLine();

        System.out.print("Enter 4-digit PIN: ");
        String pin = sc.nextLine();

        BankAccount account = bank.authenticate(accountNumber,pin);

        if(account != null) {
            System.out.println("\nWelcome, "+ account.getCustomerName());
            customerMenu(account);
        }
    }

    private static void customerMenu(BankAccount account) {
        int choice;

        do{
            System.out.println("\n====CUSTOMER DASHBOARD====");
            System.out.println("1.View Account Details");
            System.out.println("2.Check Balance");
            System.out.println("3.Deposit Money");
            System.out.println("4.Withdraw Money");
            System.out.println("5.Transfer Money");
            System.out.println("6.Transaction History");
            System.out.println("7.Logout");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:
                    account.displayAccountDetails();
                    break;

                case 2:
                    System.out.println("Current Balance: Rs. "+ account.getBalance());
                    break;

                case 3:
                    System.out.print("Enter Deposit Amount: Rs. ");
                    BigDecimal deposit = sc.nextBigDecimal();
                    sc.nextLine();

                    account.deposit(deposit);
                    break;

                case 4:
                    System.out.print("Enter Withdrawal Amount: Rs. ");
                    BigDecimal withdrawal = sc.nextBigDecimal();
                    sc.nextLine();

                    account.withdraw(withdrawal);
                    break;

                case 5:
                    System.out.print("Enter Receiver Account Number: ");
                    String receiver = sc.nextLine();

                    System.out.print("Enter Transfer Amount: Rs. ");
                    BigDecimal transfer = sc.nextBigDecimal();
                    sc.nextLine();

                    bank.transferMoney(
                        account.getAccountNumber(),
                        receiver,
                        transfer
                    );
                    break;

                case 6:
                    account.displayTransactionHistory();
                    break;

                case 7:
                    System.out.println("Logged out successfully.");
                    break;

                default:
                    System.out.println("Invalid choice.");

            }
        }while(choice!=7);
    }
}