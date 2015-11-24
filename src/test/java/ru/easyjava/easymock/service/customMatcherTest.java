package ru.easyjava.easymock.service;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import ru.easyjava.easymock.repository.OrderRepository;

import static org.easymock.EasyMock.eq;
import static ru.easyjava.easymock.service.FutureEquals.eqFuture;
import static ru.easyjava.easymock.service.testObjectsFactory.testUser;

public class customMatcherTest {
    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock
    private OrderRepository orderRepository;

    @TestSubject
    private UserService testedObject = new UserService();

    @Test
    public void testTomorrow() {
        orderRepository.addOrder(eq(testUser()), eqFuture(1));
        EasyMock.replay(orderRepository);

        testedObject.addOrder(testUser());
    }
}
