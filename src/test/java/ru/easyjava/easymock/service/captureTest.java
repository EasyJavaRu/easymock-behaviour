package ru.easyjava.easymock.service;

import org.easymock.Capture;
import org.easymock.CaptureType;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import ru.easyjava.easymock.entity.User;
import ru.easyjava.easymock.repository.GroupRepository;
import ru.easyjava.easymock.repository.UserRepository;

import static junit.framework.TestCase.fail;
import static org.easymock.EasyMock.*;
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

    @Test
    public void multipleCaptureTest() {
        Capture<User> actual = EasyMock.newCapture(CaptureType.ALL);

        userRepository.save(capture(actual));
        expectLastCall().anyTimes();

        replay(userRepository);

        testedObject.create("TEST");
        testedObject.create("SECOND");

        if (!actual.hasCaptured()) {
            fail("Save argument is not valid");
        }
        assertThat(actual.getValues().get(0).getUsername(), is("TEST"));
        assertThat(actual.getValues().get(1).getUsername(), is("SECOND"));

        /**
         * Throws java.lang.AssertionError: More than one value captured
         */
        //assertThat(actual.getValue(), is(testUser()));
    }
}
