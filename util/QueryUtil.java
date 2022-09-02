package com.jdbc.util;

public class QueryUtil {

    public static String insertAccountQuery(){
        return "INSERT INTO RECORDS(ACCNO, ACCTYPE, NAME, BALANCE) VALUES (?,?,?,?)";
    }

    public static String selectAllRecords(){
        return "SELECT * FROM RECORDS";
    }

    public static String selectAccountById(String accno){
        return "SELECT * FROM RECORDS WHERE ACCNO = " + accno;
    }

    public static String deleteAccountById(String accno){
        return "DELETE FROM RECORDS WHERE ACCNO = " + accno;
    }

    public static String updateEmployeeQuery(int employeeId){
        return "UPDATE EMPLOYEE_INFO SET EMPLOYEE_NAME = ?, EMPLOYEE_ADDRESS = ?, EMPLOYEE_SALARY = ? WHERE EMPLOYEE_ID = " + employeeId;
    }

    public static String selectEmployeeById(int employeeId){
        return "SELECT * FROM EMPLOYEE_INFO WHERE EMPLOYEE_ID = " + employeeId;
    }

    public static String loginAccount(String name){
        return "SELECT * FROM RECORDS WHERE NAME = " + name;
    }
}
