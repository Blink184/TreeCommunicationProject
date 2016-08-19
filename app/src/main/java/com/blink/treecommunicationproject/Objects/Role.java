package com.blink.treecommunicationproject.Objects;

/**
 * Created by Kirby on 5/23/2016.
 */
public class Role {

    private int id;
    private String desription;
    private boolean IsMaster;
    private boolean isDeleted;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public boolean isMaster() {
        return IsMaster;
    }

    public void setMaster(boolean master) {
        IsMaster = master;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
