package ru.easyjava.easymock.repository;

import ru.easyjava.easymock.entity.User;

import java.time.LocalDate;

/**
 * Sample repository.
 */
public interface OrderRepository {
    /**
     * Wrtites user object along with date.
     * @param user User to write.
     * @param tomorrow Date to write.
     */
    void addOrder(User user, LocalDate tomorrow);
}
