package com.blink.treecommunicationproject.Objects;

/**
 * Created by Kirby on 5/23/2016.
 */
public class UserRole {

    private int id;
    private Role role;
    private User user;
    private String username;
    private String jobTitle;
    private boolean isDeleted;

    public UserRole() {
    }

    public UserRole(int id, Role role, User user, String username, String jobTitle, boolean isDeleted) {
        this.id = id;
        this.role = role;
        this.user = user;
        this.username = username;
        this.jobTitle = jobTitle;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
