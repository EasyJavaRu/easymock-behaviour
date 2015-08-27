package ru.easyjava.easymock.service;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import ru.easyjava.easymock.entity.User;
import ru.easyjava.easymock.repository.GroupRepository;
import ru.easyjava.easymock.repository.UserRepository;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.easyjava.easymock.service.testObjectsFactory.testGroup;
import static ru.easyjava.easymock.service.testObjectsFactory.testUser;

public class UserServiceDefaultTest {
    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock(type = MockType.DEFAULT)
    private GroupRepository groupRepository;

    @Mock(type = MockType.DEFAULT)
    private UserRepository userRepository;

    @TestSubject
    private UserService testedObject = new UserService();

    //This test will fail intentionally
    @Test
    public void testNiceMock() {
        replay(groupRepository, userRepository);
        assertThat(testedObject.create("TEST"), is(testUser()));
    }

    @Test
    public void testDefaultMock() {
        groupRepository.save(testGroup());
        userRepository.save(testUser());

        replay(groupRepository, userRepository);

        assertThat(testedObject.create("TEST"), is(testUser()));
    }

    //This test will fail intentionally
    @Test
    public void testWrongUserMock() {
        User wrongUser = new User();
        wrongUser.setGroup(testGroup());
        wrongUser.setUsername("WRONG");

        groupRepository.save(testGroup());
        userRepository.save(wrongUser);

        replay(groupRepository, userRepository);

        assertThat(testedObject.create("TEST"), is(testUser()));
    }

    @Test
    public void testSavelessMock() {
        groupRepository.save(testGroup());
        userRepository.save(testUser());

        replay(groupRepository, userRepository);

        assertThat(testedObject.savelessCreate("TEST"), is(testUser()));
    }

    //This test will fail intentionally
    @Test
    public void testVerifyMock() {
        groupRepository.save(testGroup());
        userRepository.save(testUser());

        replay(groupRepository, userRepository);

        assertThat(testedObject.savelessCreate("TEST"), is(testUser()));

        verify(groupRepository, userRepository);
    }

}
