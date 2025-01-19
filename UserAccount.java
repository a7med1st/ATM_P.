import java.io.FileNotFoundException;
import java.util.ArrayList;

class UserAccount {
    private static ArrayList<String> transactionHistory = FileHandler.transactionsPrinter();
    private String fullName;
    private String phoneNumber;
    private String password;
    private String accountNumber;
    private double balance;

    public UserAccount(String fullName, String phoneNumber, String password, String accountNumber, double balance) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean deposit(double amount) {
        java.util.Date date = new java.util.Date();
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Account Number: " + accountNumber + "  ||  " + amount + "$  ||  " + "deposit" + "  ||  " + date.toGMTString());
            try {
                FileHandler.transactionsWriter(transactionHistory);
            } catch (FileNotFoundException ex) {
                System.out.println("Error reading file: " + ex.getMessage());
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        java.util.Date date = new java.util.Date();
        if (amount > this.balance) {
            return false;
        } else {
            this.balance -= amount;
            try {
                FileHandler.transactionsWriter(transactionHistory);
            } catch (FileNotFoundException ex) {
                System.out.println("Error reading file: " + ex.getMessage());
            }
            transactionHistory.add("Account Number: " + accountNumber + "  ||  " + amount + "$  ||  " + "deposit" + "  ||  " + date.toGMTString());
            return true;
        }
    }

    public ArrayList<String> viewTransactionHistory() {

        ArrayList<String> transactionHistoryForUser = new ArrayList<>();

        for (String A : transactionHistory) {
            if (A.contains(accountNumber)) {
                transactionHistoryForUser.add(A);
            }
        }
        return transactionHistoryForUser;
    }

    @Override
    public String toString() {
        return accountNumber + ", " + fullName + ", " + phoneNumber + ", " + password + ", " + balance;
    }
}
