package ru.easyjava.easymock.entity;

/**
 * Sample group object.
 */
public class Group {
    /**
     * We need to distinguish groups from each other.
     */
    private String groupName;

    /**
     * groupName getter.
     * @return current name.
     */
    public final String getGroupName() {
        return groupName;
    }

    /**
     * groupName setter.
     * @param g new name.
     */
    public final void setGroupName(final String g) {
        this.groupName = g;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Group)) {
            return false;
        }

        Group group = (Group) o;

        return groupName.equals(group.groupName);

    }

    @Override
    public final int hashCode() {
        return groupName.hashCode();
    }
}
