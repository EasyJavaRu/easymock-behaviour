package ru.easyjava.easymock.entity;

/**
 * Sample user object.
 */
public class User {
    /**
     * We need to distinguish users from each other.
     */
    private String username;
    /**
     * Users belong to the groups.
     */
    private Group group;

    /**
     * username getter.
     * @return current username.
     */
    public final String getUsername() {
        return username;
    }

    /**
     * username setter.
     * @param u new username.
     */
    public final void setUsername(final String u) {
        this.username = u;
    }

    /**
     * group getter.
     * @return current group.
     */
    public final Group getGroup() {
        return group;
    }

    /**
     * group setter.
     * @param g new group.
     */
    public final void setGroup(final Group g) {
        this.group = g;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        return username.equals(user.username) && group.equals(user.group);

    }

    @Override
    public final int hashCode() {
        int result = username.hashCode();
        result = result + group.hashCode();
        return result;
    }
}
