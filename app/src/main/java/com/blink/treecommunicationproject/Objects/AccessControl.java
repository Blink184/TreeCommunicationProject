package com.blink.treecommunicationproject.Objects;

/**
 * Created by Kirby on 5/23/2016.
 */
public class AccessControl {

    private int id;
    private boolean canAccessSiblingsByRole;
    private boolean canAccessSiblingsByParent;
    private boolean canAccessChildren;
    private boolean canCommunicateWithSiblings;

    public AccessControl() {
    }

    public AccessControl(int id, boolean canAccessSiblingsByRole, boolean canAccessSiblingsByParent, boolean canAccessChildren, boolean canCommunicateWithSiblings) {
        this.id = id;
        this.canAccessSiblingsByRole = canAccessSiblingsByRole;
        this.canAccessSiblingsByParent = canAccessSiblingsByParent;
        this.canAccessChildren = canAccessChildren;
        this.canCommunicateWithSiblings = canCommunicateWithSiblings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCanAccessSiblingsByRole() {
        return canAccessSiblingsByRole;
    }

    public void setCanAccessSiblingsByRole(boolean canAccessSiblingsByRole) {
        this.canAccessSiblingsByRole = canAccessSiblingsByRole;
    }

    public boolean isCanAccessSiblingsByParent() {
        return canAccessSiblingsByParent;
    }

    public void setCanAccessSiblingsByParent(boolean canAccessSiblingsByParent) {
        this.canAccessSiblingsByParent = canAccessSiblingsByParent;
    }

    public boolean isCanAccessChildren() {
        return canAccessChildren;
    }

    public void setCanAccessChildren(boolean canAccessChildren) {
        this.canAccessChildren = canAccessChildren;
    }

    public boolean isCanCommunicateWithSiblings() {
        return canCommunicateWithSiblings;
    }

    public void setCanCommunicateWithSiblings(boolean canCommunicateWithSiblings) {
        this.canCommunicateWithSiblings = canCommunicateWithSiblings;
    }
}
