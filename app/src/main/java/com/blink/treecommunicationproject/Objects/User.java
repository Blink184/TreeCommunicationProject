package com.blink.treecommunicationproject.Objects;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Kirby on 5/23/2016.
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String email;
    private boolean isAdmin;
    private Date lastActiveDate;
    private boolean isLoggedIn;
    private boolean isBanned;
    private boolean isDeleted;
//    private Bitmap image;

    public User() {
    }

    public User(int id, String firstName, String lastName, String phoneNmber, boolean isDeleted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phoneNmber;
        this.isDeleted = isDeleted;
    }

    public User(int id, String firstName, String lastName, String username, String password, String phone, String address, String email, boolean isAdmin, Date lastActiveDate, boolean isDeleted, Bitmap image, boolean isBanned, boolean isLoggedIn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.isAdmin = isAdmin;
        this.lastActiveDate = lastActiveDate;
        this.isDeleted = isDeleted;
//        this.image = image;
        this.isBanned = isBanned;
        this.isLoggedIn = isLoggedIn;
    }

    public String getFullName() {
        return firstName+ ' '+lastName;
    }

    public String toJson() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("Id", id);
        object.put("Username", username);
        object.put("Password", password);
        object.put("FirstName", firstName);
        object.put("LastName", lastName);
        object.put("Phone", phone);
        object.put("Address", address);
        object.put("Email", email);
        object.put("LastActiveDate", lastActiveDate);
        object.put("IsAdmin", isAdmin);
        object.put("IsBanned", isBanned);
        object.put("IsDeleted", isDeleted);
//        object.put("image", image);
        return object.toString();
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setLastActiveDate(Date lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

//    public void setImage(Bitmap image) {
//        this.image = image;
//    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Date getLastActiveDate() {
        return lastActiveDate;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

//    public Bitmap getImage() {
//        return image;
//    }
}
