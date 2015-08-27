package ru.easyjava.easymock.service;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import ru.easyjava.easymock.repository.GroupRepository;
import ru.easyjava.easymock.repository.UserRepository;

import static org.easymock.EasyMock.replay;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.easyjava.easymock.service.testObjectsFactory.testUser;

public class UserServiceNiceTest {
    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock(type = MockType.NICE)
    private GroupRepository groupRepository;

    @Mock(type = MockType.NICE)
    private UserRepository userRepository;

    @TestSubject
    private UserService testedObject = new UserService();

    @Test
    public void testNiceMock() {
        replay(groupRepository, userRepository);
        assertThat(testedObject.create("TEST"), is(testUser()));
    }

    @Test
    public void testSavelessNiceMock() {
        replay(groupRepository, userRepository);
        assertThat(testedObject.savelessCreate("TEST"), is(testUser()));
    }
}