package com.blink.treecommunicationproject.Objects;

/**
 * Created by Kirby on 8/24/2016.
 */
public class BroadcastLine {
    private int id;
    private int broadcastId;
    private int toUserRoleId;
    private String dateReceived;
    private boolean isReceived;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(int broadcastId) {
        this.broadcastId = broadcastId;
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
