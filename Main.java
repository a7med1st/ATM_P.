
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMSystem atmSystem = new ATMSystem();

        while(true) {
            System.out.println("--- ATM System ---");
            System.out.println("1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    UserAccount user = atmSystem.login(accountNumber, password);
                    if (user != null) {
                        System.out.println("Login successful! Welcome, " + user.getFullName());
                        boolean loggedIn = true;

                        while(loggedIn) {
                            System.out.println("\n1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. View Transactions");
                            System.out.println("5. Logout");
                            System.out.print("Choose an option: ");
                            int option1 = scanner.nextInt();
                            switch (option1) {
                                case 1:
                                    System.out.print("Enter deposit amount: ");
                                    double depositAmount = scanner.nextDouble();
                                    atmSystem.performTransaction(user, "deposit", depositAmount);
                                    break;
                                case 2:
                                    System.out.print("Enter withdrawal amount: ");
                                    double withdrawalAmount = scanner.nextDouble();
                                    atmSystem.performTransaction(user, "withdraw", withdrawalAmount);
                                    break;
                                case 3:
                                    atmSystem.performTransaction(user, "balance", (double)0.0F);
                                    break;
                                case 4:

                                    System.out.println("\t\t ||Transaction History:||  \t\t");
                                    for (String transaction : user.viewTransactionHistory()) {
                                        System.out.println(transaction);
                                        break;
                                    }
                                case 5:
                                    loggedIn = false;
                                    System.out.println("Logged out successfully.");
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                        }
                        break;
                    }

                    System.out.println("Invalid account number or password. Please try again.");
                    break;
                case 2:
                    System.out.print("Enter Full Name: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String newPassword = scanner.nextLine();

                    double initialBalance = 0;
                    atmSystem.createNewAccount(fullName, phoneNumber, newPassword, initialBalance);
                    break;
                case 3:
                    System.out.println("Thank you for using the ATM system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
