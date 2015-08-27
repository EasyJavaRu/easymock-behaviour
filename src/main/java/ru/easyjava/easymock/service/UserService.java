package ru.easyjava.easymock.service;

import ru.easyjava.easymock.entity.Group;
import ru.easyjava.easymock.entity.User;
import ru.easyjava.easymock.repository.GroupRepository;
import ru.easyjava.easymock.repository.UserRepository;

/**
 * Sample service.
 */
public class UserService {
    /**
     * Sample dependency.
     */
    private UserRepository userRepository;

    /**
     * Sample dependency.
     */
    private GroupRepository groupRepository;

    /**
     * Default constructor for injection.
     */
    public UserService() { }

    /**
     * Constructor for constructor based injection.
     *
     * @param u UserRepository dependency.
     * @param g GroupRepository dependency.
     */
    public UserService(final UserRepository u,
                       final GroupRepository g) {
        this.userRepository = u;
        this.groupRepository = g;
    }

    /**
     * Creates user and default group for him.
     * @param login username.
     * @return fully constructed user.
     */
    final User create(final String login) {
        Group g = new Group();
        g.setGroupName(login);
        groupRepository.save(g);

        User u = new User();
        u.setUsername(login);
        u.setGroup(g);
        userRepository.save(u);

        return u;
    }

    /**
     * Creates user and default group for him.
     * Erroneously doesn't saves user to the database.
     * @param login username.
     * @return fully constructed user.
     */
    final User savelessCreate(final String login) {
        Group g = new Group();
        g.setGroupName(login);
        groupRepository.save(g);

        User u = new User();
        u.setUsername(login);
        u.setGroup(g);

        return u;
    }
}
