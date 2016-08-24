package com.blink.treecommunicationproject.Objects;

/**
 * Created by Kirby on 8/24/2016.
 */
public class Broadcast {
    private int id;
    private int fromUserRoleId;
    private int attachmentId;
    private String title;
    private String content;
    private String dateSent;
    private boolean isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUserRoleId() {
        return fromUserRoleId;
    }

    public void setFromUserRoleId(int fromUserRoleId) {
        this.fromUserRoleId = fromUserRoleId;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
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

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
