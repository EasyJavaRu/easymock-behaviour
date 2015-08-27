package ru.easyjava.easymock.service;

import ru.easyjava.easymock.entity.Group;
import ru.easyjava.easymock.entity.User;

public class testObjectsFactory {
    public static Group testGroup() {
        Group g = new Group();

        g.setGroupName("TEST");

        return g;
    }

    public static User testUser() {
        User u = new User();

        u.setUsername("TEST");
        u.setGroup(testGroup());

        return u;
    }
}
