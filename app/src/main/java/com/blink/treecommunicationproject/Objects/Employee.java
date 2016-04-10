package com.blink.treecommunicationproject.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ahmad hammoud on 4/10/2016.
 */
public class Employee {


    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private EmployeeType employeeType;
    private Employee parentEmployee;
    private List<Employee> childrenEmployees;
    private String username;
    private String password;

    public Employee(int id, String firstName, String lastName, String phoneNumber, EmployeeType employeeType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.employeeType = employeeType;
    }


    public Employee(int id, String username, String password, String firstName, String lastName, String phoneNumber, EmployeeType employeeType, Employee parentEmployee, List<Employee> childrenEmployees) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.employeeType = employeeType;
        this.parentEmployee = parentEmployee;
        this.childrenEmployees = childrenEmployees;
        this.username = username;
        this.password = password;
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /*public String toJson() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("firstName", firstName);
        object.put("lastName", lastName);
        object.put("password", password);
        return object.toString();
    }*/

    public enum EmployeeType{
        President, Director, Advisor, Responsible, Member, Person
    }
}
