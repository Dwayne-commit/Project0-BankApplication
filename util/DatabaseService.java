package com.jdbc.util;

import java.sql.*;

public class DatabaseService {
    DatabaseUtil databaseUtil = new DatabaseUtil();

    public void insertRecord(Account account) throws SQLException {

        Connection connection = databaseUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertAccountQuery());

        preparedStatement.setString(1, account.getAccno());
        preparedStatement.setString(2, account.getAcc_type());
        preparedStatement.setString(3, account.getName());
        preparedStatement.setLong(4, account.getBalance());

        int rows = preparedStatement.executeUpdate();

        if (rows > 0) {
            System.out.println("Record Created Successfully");
        } else {
            System.out.println("Insert record fail");
        }
    }

    public void getAllRecords() throws SQLException {
        Connection connection = databaseUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllRecords());

        while (resultSet.next()) {
            printRecords(new Account(resultSet.getString("ACCNO"), resultSet.getString("ACCTYPE"), resultSet.getString("NAME"), resultSet.getLong("BALANCE")));
        }
    }

    private void printRecords(Account account) {
        System.out.println("Account Number: " + account.getAccno());
        System.out.println("Account Type: " + account.getAcc_type());
        System.out.println("User Name: " + account.getName());
        System.out.println("Current Balance: " + account.getBalance());
        System.out.println("------------------------------------------");

    }

    public boolean getAccountById(String accno) throws SQLException {

        boolean isFound = false;

        Connection connection = databaseUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryUtil.selectAccountById(accno));

        if (resultSet.next()) {
            isFound = true;
            printRecords(new Account(resultSet.getString("ACCNO"), resultSet.getString("ACCTYPE"), resultSet.getString("NAME"), resultSet.getLong("BALANCE")));
        } else {
            System.out.println("Record Not Found!");
        }
        return isFound;
    }

    public void deleteAccountById(String name) throws SQLException{
        Connection connection = databaseUtil.getConnection();
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(QueryUtil.deleteAccountById(name));

        if(rows > 0){
            System.out.println("Record deleted successfully");
        } else {
            System.out.println("Something went wrong!");
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        Connection connection = databaseUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.updateEmployeeQuery(employee.getEmployeeId()));
        preparedStatement.setString(1, employee.getEmployeeName());
        preparedStatement.setString(2,employee.getEmployeeAddress());
        preparedStatement.setDouble(3,employee.getEmployeeSalary());

        int rows = preparedStatement.executeUpdate();
        if(rows > 0){
            System.out.println("Record Updated Successfully!");
        } else {
            System.out.println("Failed to Update!!");
        }
    }

    private void printEmployee(Employee employee) throws SQLException {
        System.out.println("Employee id :" + employee.getEmployeeId());
        System.out.println("Employee name " + employee.getEmployeeName());
        System.out.println("Employee address" + employee.getEmployeeAddress());
        System.out.println("Employee Salary: " + employee.getEmployeeSalary());
        System.out.println("---------------------------------------------");
    }

    public boolean getEmployeeById(int id) throws SQLException {

        boolean isFound = false;

        Connection connection = databaseUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryUtil.selectEmployeeById(id));

        if(resultSet.next()){
            isFound = true;
            printEmployee(new Employee(resultSet.getInt("EMPLOYEE_ID"), resultSet.getString("EMPLOYEE_NAME"), resultSet.getString("EMPLOYEE_ADDRESS"), resultSet.getDouble("EMPLOYEE_SALARY")));
        } else {
            System.out.println("Record not found for id " + id);
        }
        return isFound;
    }

    public void callableExample() throws SQLException {
        Connection connection = databaseUtil.getConnection();
        CallableStatement stmt = connection.prepareCall("{call emp()}");
        Boolean hasResult = stmt.execute();
        if(hasResult){
            ResultSet res = stmt.getResultSet();
            while(res.next()){
                System.out.println(res.getString("employee_name"));
                System.out.println(res.getString("employee_address"));
                System.out.println(res.getDouble("employee_salary"));
            }
        }
        stmt.close();
    }
}

