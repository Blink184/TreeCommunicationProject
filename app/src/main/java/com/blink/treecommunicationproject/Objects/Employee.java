package com.blink.treecommunicationproject.Objects;

import android.graphics.Bitmap;

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
    private Bitmap picture;
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


    public Employee(int id, String username, String password, String firstName, String lastName, String phoneNumber, Bitmap picture, EmployeeType employeeType, Employee parentEmployee, List<Employee> childrenEmployees) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
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

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public boolean hasParentEmployee() {
        return getParentEmployee() != null;
    }


    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Employee getParentEmployee() {
        return parentEmployee;
    }

    public void setParentEmployee(Employee parentEmployee) {
        this.parentEmployee = parentEmployee;
    }

    public List<Employee> getChildrenEmployees() {
        return childrenEmployees;
    }

    public void setChildrenEmployees(List<Employee> childrenEmployees) {
        this.childrenEmployees = childrenEmployees;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

}
