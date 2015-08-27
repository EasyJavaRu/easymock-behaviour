package ru.easyjava.easymock.repository;

import ru.easyjava.easymock.entity.Group;

/**
 * Sample repository.
 */
public interface GroupRepository {
    /**
     * Saves group object into database.
     * @param g object to save.
     */
    void save(Group g);
}
