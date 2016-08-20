package com.blink.treecommunicationproject.Objects;

import java.util.Date;

/**
 * Created by Kirby on 4/12/2016.
 */
public class Task {
    private int id;
    private int fromUserRoleId;
    private int toUserRoleId;
    private String fromUserRole;
    private String toUserRole;
    private String title;
    private String content;
    private int taskState;
    private String dueDate;
    private String startDate;
    private boolean isCanceled;
    private boolean isDeleted;
    private boolean isDone;
    private int delegatedToUserRoleId;
    private Date dateAssigned;
    private Date dateDone;
    private Date cancelDate;
    private Date dateReceived;
    private String DelegatedToUserRole;

    public Task() {
    }

    public String getDelegatedToUserRole() {
        return DelegatedToUserRole;
    }

    public void setDelegatedToUserRole(String delegatedToUserRole) {
        DelegatedToUserRole = delegatedToUserRole;
    }

    public int getFromUserRoleId() {
        return fromUserRoleId;
    }

    public void setFromUserRoleId(int fromUserRoleId) {
        this.fromUserRoleId = fromUserRoleId;
    }

    public int getToUserRoleId() {
        return toUserRoleId;
    }

    public void setToUserRoleId(int toUserRoleId) {
        this.toUserRoleId = toUserRoleId;
    }

    public String getFromUserRole() {
        return fromUserRole;
    }

    public void setFromUserRole(String fromUserRole) {
        this.fromUserRole = fromUserRole;
    }

    public String getToUserRole() {
        return toUserRole;
    }

    public void setToUserRole(String toUserRole) {
        this.toUserRole = toUserRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getDelegatedToUserRoleId() {
        return delegatedToUserRoleId;
    }

    public void setDelegatedToUserRoleId(int delegatedToUserRoleId) {
        this.delegatedToUserRoleId = delegatedToUserRoleId;
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

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }
}
