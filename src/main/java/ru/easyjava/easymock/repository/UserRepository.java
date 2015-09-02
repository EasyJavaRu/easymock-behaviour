package ru.easyjava.easymock.repository;

import ru.easyjava.easymock.entity.User;

/**
 * Sample repository.
 */
public interface UserRepository {
    /**
     * Finds user by it's login.
     * @param login username to search
     * @return User object or null if not found.
     */
    User findOne(String login);
    /**
     * Saves user object into database.
     * @param u object to save.
     */
    void save(User u);
}
