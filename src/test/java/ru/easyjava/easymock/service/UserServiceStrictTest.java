package ru.easyjava.easymock.service;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import ru.easyjava.easymock.repository.GroupRepository;
import ru.easyjava.easymock.repository.UserRepository;

import static org.easymock.EasyMock.createStrictControl;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.easyjava.easymock.service.testObjectsFactory.testGroup;
import static ru.easyjava.easymock.service.testObjectsFactory.testUser;

public class UserServiceStrictTest {
    IMocksControl ctrl = createStrictControl();

    private GroupRepository groupRepository = ctrl.createMock(GroupRepository.class);

    private UserRepository userRepository = ctrl.createMock(UserRepository.class);

    private UserService testedObject = new UserService(userRepository, groupRepository);

    @Before
    public void setUp() {
        ctrl.reset();
    }

    @Test
         public void testStrictMock() {
        groupRepository.save(testGroup());
        userRepository.save(testUser());

        ctrl.replay();

        assertThat(testedObject.create("TEST"), is(testUser()));

        ctrl.verify();
    }

    //This test will fail intentionally
    @Test
    public void testWrongOrderMock() {
        userRepository.save(testUser());
        groupRepository.save(testGroup());

        ctrl.replay();

        assertThat(testedObject.create("TEST"), is(testUser()));

        ctrl.verify();
    }
}
