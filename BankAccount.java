import java.math.BigDecimal;
import java.util.ArrayList;

public class BankAccount {

    private String accountNumber;
    private String customerName;
    private String pin;
    private BigDecimal balance;

    private static final BigDecimal MINIMUM_BALANCE=new BigDecimal("500.00");

    private ArrayList<Transaction>transactions = new ArrayList<>();

    public BankAccount(String accountNumber,String customerName,String pin,BigDecimal initialBalance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.pin = pin;
        this.balance = initialBalance;

        transactions.add(new Transaction(
            TransactionType.DEPOSIT,
            initialBalance,
            "Initial account deposit"
        ));
    }
    
    public String getAccountNumber() {
        return accountNumber;

    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public boolean deposit(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO)<=0) {
            System.out.println("Invalid deposit amount. ");
            return false;
        }

        balance = balance.add(amount);

        transactions.add(new Transaction(
            TransactionType.DEPOSIT,
            amount,
            "Money deposited"
        ));

        System.out.println("Deposit successful");
        return true;
    }
public boolean withdraw(BigDecimal amount) {

    if (amount.compareTo(BigDecimal.ZERO)<=0) {
        System.out.println("Invalid withdrawal amount. ");
        return false;

    }

    BigDecimal reamainingBalance = balance.subtract(amount);

    if(reamainingBalance.compareTo(MINIMUM_BALANCE) < 0) {
        System.out.println("Insufficient balance or minimum balance. ");
        return false;
    }

    balance = reamainingBalance;

    transactions.add(new Transaction(
        TransactionType.WITHDRAWAL,
        amount,
        "Money withdrawn"
    ));

    System.out.println("Withdrawal successful");
    return true;
}

public void displayAccountDetails() { 

    System.out.println("\n====ACCOUNT DETAILS====");
    System.out.println("Account Number: "+accountNumber);
    System.out.println("Cuctomer Name: "+ customerName);
    System.out.println("Balance: Rs. "+ balance);
}

public void displayTransactionHistory() {
    System.out.println("\n===TRANSACTION HISTORY===");

    if(transactions.isEmpty()) {
        System.out.println("No transaction found. ");
        return;
    }

for(Transaction transaction: transactions) {
    transaction.displayTransaction();

}
}
}