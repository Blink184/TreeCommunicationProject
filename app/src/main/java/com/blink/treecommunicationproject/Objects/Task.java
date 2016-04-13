package com.blink.treecommunicationproject.Objects;

import java.util.Date;

/**
 * Created by Kirby on 4/12/2016.
 */
public class Task {
    private int id;
    private Employee fromEmployee;
    private Employee toEmployee;
    private Date dateAssigned;
    private Date dateDone;
    private boolean isDone;
    private String description;

    public Task(int id, Employee fromEmployee, Employee toEmployee, Date dateAssigned, String description) {
        this.id = id;
        this.fromEmployee = fromEmployee;
        this.toEmployee = toEmployee;
        this.dateAssigned = dateAssigned;
        this.dateDone = null;
        this.isDone = false;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getFromEmployee() {
        return fromEmployee;
    }

    public void setFromEmployee(Employee fromEmployee) {
        this.fromEmployee = fromEmployee;
    }

    public Employee getToEmployee() {
        return toEmployee;
    }

    public void setToEmployee(Employee toEmployee) {
        this.toEmployee = toEmployee;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public Date getDateDone() {
        return dateDone;
    }

    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
