package com.jdbc.util;
import java.sql.SQLException;
import java.util.Scanner;
class BankDetails {
    private String accno;
    private String name;
    private String acc_type;
    private long balance;
    private long ac_noA;
    private long ac_noT;
    Scanner sc = new Scanner(System.in);
    //method to open new account
    public void openAccount() throws SQLException {
        try {
            DatabaseService databaseService = new DatabaseService();
            System.out.print("Enter Account No: ");
            accno = sc.next();
            System.out.print("Enter Account type: ");
            acc_type = sc.next();
            System.out.print("Enter Name: ");
            name = sc.next();
            System.out.print("Enter Balance: ");
            balance = sc.nextLong();
            databaseService.insertRecord(new Account(accno, name, acc_type, balance));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    //method to display account details
    public void showAccount() {
        try {
            DatabaseService databaseService = new DatabaseService();
            System.out.println("Name of account holder: " + name);
            System.out.println("Account no.: " + accno);
            System.out.println("Account type: " + acc_type);
            System.out.println("Balance: " + balance);
            databaseService.getAllRecords();
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    //method to deposit money
    public void deposit() {
        long amt;
        System.out.println("Enter the amount you want to deposit: ");
        amt = sc.nextLong();
        if(balance >= amt){
            balance = balance + amt;
            System.out.println("Balance After Withdrawal: " + balance);
        } else {
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!");
        }
    }
    //method to withdraw money
    public void withdrawal() {
        long amt;
        System.out.println("Enter the amount you want to withdraw: ");
        amt = sc.nextLong();
        if (balance >= amt) {
            balance = balance - amt;
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!" );
        }
    }

    public int add(int numA, int numB){
        return numA + numB;
    }



    //method to search an account number
    public boolean search(String ac_no) {
        if (accno.equals(ac_no)) {
            showAccount();
            return (true);
        }
        return (false);
    }
    public void transfer(){
        int fee = 5;
        System.out.println("Enter Amount you need");
        int amn = sc.nextInt();
        if(balance >= amn){
            balance = balance + amn;
            balance = balance - fee;
            System.out.println("New balance:" + balance);
        } else {
            System.out.println("Insufficient funds");
        }
    }
}

public class BankingApp {
    public static void main(String arg[])  throws SQLException{

        Scanner sc = new Scanner(System.in);
        //create initial accounts
            System.out.print("How many number of customers do you want to input? ");
            int n = sc.nextInt();
            BankDetails C[] = new BankDetails[n];
            for (int i = 0; i < C.length; i++) {
                C[i] = new BankDetails();
                C[i].openAccount();
        }
        // loop runs until number 5 is not pressed to exit
        int ch;
        do {
            System.out.println("\n ***Banking System Application***");
            System.out.println("1. Display all account details \n 2. Search by Account number\n 3. Deposit the amount \n 4. Withdraw the amount \n 5. Transfer Money \n 6. Delete An Account \n 7. Update Employee Records \n 8. Display Employee Records \n 9.Exit ");
            System.out.println("Enter your choice: ");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Hi Employee : ");
                    System.out.println("Enter Employee Number: ");
                    int pin = sc.nextInt();
                    if(pin == 82) {
                        for (int i = 0; i < C.length; i++) {
                            C[i].showAccount();
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter account no. you want to search: ");
                    DatabaseService databaseService = new DatabaseService();
                    String ac_no = sc.next();
                    databaseService.getAccountById(ac_no);
                    boolean found = false;
                    for (int i = 0; i < C.length; i++) {
                        found = C[i].search(ac_no);
                        if (found) {
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Account no. : ");
                    ac_no = sc.next();
                    found = false;
                    for (int i = 0; i < C.length; i++) {
                        found = C[i].search(ac_no);
                        if (found) {
                            C[i].deposit();
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                    }
                    break;
                case 4:
                    System.out.print("Enter Account No : ");
                    ac_no = sc.next();
                    found = false;
                    for (int i = 0; i < C.length; i++) {
                        found = C[i].search(ac_no);
                        if (found) {
                            C[i].withdrawal();
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                    }
                    break;
                case 5:
                    System.out.print("Enter Account No A: ");
                    ac_no = sc.next();
                    System.out.println("Enter Account No T: ");
                    ac_no = sc.next();
                    found = false;
                    for (int i = 0; i < C.length; i++) {
                        found = C[i].search(ac_no);
                        if (found) {
                            System.out.println("Accept Transfer 1 Decline 2 ");
                            int co = sc.nextInt();
                            if(co == 1) {
                                C[i].transfer();
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                    break;
                case 6:
                    System.out.println("Hi Employee: ");
                    System.out.println("Enter Employee Account Number: ");
                    int bu = sc.nextInt();
                    if(bu == 82){
                        DatabaseService databaseServices = new DatabaseService();
                        System.out.println("Enter Account Number: ");
                        String ac_noo = sc.next();
                        databaseServices.deleteAccountById(ac_noo);
                        System.out.println("Record Deleted!");
                    } else {
                        System.out.println("Invalid Entry!");
                    }
                    break;
                case 7:
                    System.out.println("Enter id of an employee");
                    DatabaseService databaseService6 = new DatabaseService();
                    int updateId = sc.nextInt();
                    double updateRec = sc.nextDouble();
                    boolean isFound = databaseService6.getEmployeeById(updateId);
                    if(isFound){
                        System.out.println("Enter Name, address, salary");
                        DatabaseService databaseService7 = new DatabaseService();
                        Employee employee = new Employee(updateId, sc.nextLine(), sc.nextLine(), updateRec);
                        databaseService7.updateEmployee(employee);
                    }
                    break;
                case 8:
                    DatabaseService pro = new DatabaseService();
                    pro.callableExample();
                    break;
                case 9:
                    System.out.println("See you soon...");
                    break;
            }
        }
        while (ch != 9);
    }
}