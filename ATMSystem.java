
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ATMSystem {
    private ArrayList<UserAccount> Users;

    public ATMSystem() {

        this.Users = FileHandler.usersPrinter();

    }

    public void createNewAccount(String fullName, String phoneNumber, String password, double initialBalance) {
        String accountNumber = this.generateAccountNumber();
        UserAccount newUser = new UserAccount(fullName, phoneNumber, password, accountNumber,0);
        this.Users.add(newUser);
        try {
            FileHandler.usersWriter(Users);
        } catch (FileNotFoundException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        System.out.println("Account created successfully! Your Account Number: " + accountNumber);
    }

    public UserAccount login(String accountNumber, String password) {
        for (UserAccount user : this.Users) {
            if (user.getAccountNumber().equals(accountNumber) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public void performTransaction(UserAccount user, String action, double amount) {
        switch (action.toLowerCase()) {
            case "deposit":
                user.deposit(amount);
                System.out.println("Deposit successful! New Balance: " + user.getBalance());
                try {
                    FileHandler.usersWriter(Users);
                } catch (FileNotFoundException ex) {
                    System.out.println("Error reading file: " + ex.getMessage());
                }
                break;
            case "withdraw":
                if (user.withdraw(amount)) {
                    System.out.println("Withdrawal successful! New Balance: " + user.getBalance());
                } else {
                    System.out.println("Insufficient balance.");
                }
                try {
                    FileHandler.usersWriter(Users);
                } catch (FileNotFoundException ex) {
                    System.out.println("Error reading file: " + ex.getMessage());
                }
                break;
            case "balance":
                System.out.println("Current Balance: " + user.getBalance());
                break;

            default:
                System.out.println("Invalid transaction type.");
        }
    }

    private String generateAccountNumber() {
        return String.format("%07d", (new Random()).nextInt(9999999));
    }
}
