package com.blink.treecommunicationproject.Objects;

/**
 * Created by Kirby on 5/23/2016.
 */
public class Role {

    private int id;
    private AccessControl accessControl;
    private String desription;
    private boolean isDeleted;

    public Role() {
    }

    public Role(int id, AccessControl accessControl, String desription, boolean isDeleted) {
        this.id = id;
        this.accessControl = accessControl;
        this.desription = desription;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccessControl getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(AccessControl accessControl) {
        this.accessControl = accessControl;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
