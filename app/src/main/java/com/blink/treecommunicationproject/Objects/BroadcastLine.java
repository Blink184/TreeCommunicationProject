package com.blink.treecommunicationproject.Objects;

/**
 * Created by Kirby on 8/24/2016.
 */
public class BroadcastLine {
    private int id;
    private int toUserRoleId;
    private UserRole toUserRole;
    private String dateReceived;
    private boolean isSender;
    private boolean isReceived;

    public BroadcastLine() {
    }

    public int getId() {
        return id;
    }

    public boolean isSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }

    public UserRole getToUserRole() {
        return toUserRole;
    }

    public void setToUserRole(UserRole toUserRole) {
        this.toUserRole = toUserRole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToUserRoleId() {
        return toUserRoleId;
    }

    public void setToUserRoleId(int toUserRoleId) {
        this.toUserRoleId = toUserRoleId;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }
}
