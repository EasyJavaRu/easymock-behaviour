package ru.easyjava.easymock.service;

import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;
import ru.easyjava.easymock.entity.User;
import ru.easyjava.easymock.repository.GroupRepository;
import ru.easyjava.easymock.repository.UserRepository;

import static junit.framework.TestCase.fail;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.easyjava.easymock.service.testObjectsFactory.testUser;

public class captureTest {
    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock(type = MockType.DEFAULT)
    private GroupRepository groupRepository;

    @Mock(type = MockType.DEFAULT)
    private UserRepository userRepository;

    @TestSubject
    private UserService testedObject = new UserService();

    @Test
    public void simpleCaptureTest() {
        Capture<User> actual = EasyMock.newCapture();

        userRepository.save(capture(actual));

        replay(userRepository);

        testedObject.create("TEST");

        if (!actual.hasCaptured()) {
            fail("Save argument is not valid");
        }
        assertThat(actual.getValue(), is(testUser()));
    }
}
