package ru.easyjava.easymock.repository;

import ru.easyjava.easymock.entity.User;

/**
 * Sample repository.
 */
public interface UserRepository {
    /**
     * Saves user object into database.
     * @param u object to save.
     */
    void save(User u);
}
