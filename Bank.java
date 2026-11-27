import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Bank {

    private HashMap<String,BankAccount>accounts= new HashMap<>();

    private int accountCounter = 1001;

    private String generateAccountNumber() {
        return "ACC" + accountCounter++;
    }

    public BankAccount createAccount(
        String customerName,
        String pin,
        BigDecimal initialBalance) {

        if (customerName == null || customerName.trim().isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return null;
        }
        if (pin == null || !pin.matches("\\d{4}")) {
            System.out.println("PIN must contain exactly 4 digits. ");
            return null;
        }

        if (initialBalance == null || initialBalance.compareTo(new BigDecimal("500"))<0) {
            System.out.println("Minimum opening balance is Rs.500.");
            return null;
        }

        String accountNumber = generateAccountNumber();

        BankAccount account = new BankAccount(
            accountNumber,
            customerName,
            pin,
            initialBalance
        );

        accounts.put(accountNumber,account);

        System.out.println("\nAccount created successfully.");
        System.out.println("Your Account Number: "+accountNumber);

        return account;

        }

        public BankAccount findAccount(String accountNumber) {
            return accounts.get(accountNumber);

        }

        public BankAccount authenticate(
            String accountNumber,
            String pin) {

                BankAccount account = findAccount(accountNumber);

                if(account == null) {
                    System.out.println("Account not found.");
                    return null;
                }

                if(!account.validatePin(pin)) {
                    System.out.println("Incorrect PIN.");
                    return null;
                }

                return account;
            }

            public boolean transferMoney(
                String senderNumber,
                String receiverNumber,
                BigDecimal amount) {

                if(senderNumber.equals(receiverNumber)) {
                    System.out.println("Cannot transfer to the same accoun.");
                    return false;
                }

                BankAccount sender = findAccount(senderNumber);
                BankAccount receiver = findAccount(receiverNumber);
                
                if(sender == null || receiver == null) {
                    System.out.println("Sender or receiver account not found.");
                    return false;
                }
                
                if (amount == null || amount.compareTo(BigDecimal.ZERO)<=0) {
                    System.out.println("Invalid transfer amount.");
                    return false;
                }

                if(!sender.withdraw(amount)){ 
                    return false;
                }

                receiver.deposit(amount);

                System.out.println("Transfer complete successfully");
                return true;

                }

                public void displayAllAccounts() {

                    if(accounts.isEmpty()) {
                        System.out.println("No bank account registered.");
                        return;
                    }

                    System.out.println("\n====REGISTERED ACCOUNTS====");

                    for(Map.Entry<String,BankAccount>entry : accounts.entrySet()) {
                        BankAccount account = entry.getValue();

                        System.out.println("Account Number: "+ account.getAccountNumber());
                        System.out.println("CustomerName:"+account.getCustomerName());
                        System.out.println("Balance: Rs. "+ account.getBalance());

                        System.out.println("--------------------");
                 }
           }


     }
            
        

    