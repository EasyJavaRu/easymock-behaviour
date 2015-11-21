package ru.easyjava.easymock.service;

import org.easymock.EasyMockRule;
import org.easymock.IAnswer;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;
import ru.easyjava.easymock.entity.User;
import ru.easyjava.easymock.repository.UserRepository;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.easyjava.easymock.service.testObjectsFactory.testUser;

public class resultGenerationTest {
    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock
    private UserRepository testedObject;

    @Test
    public void testMutatedUser() {
        expect(testedObject.findOne("TEST")).andStubAnswer(() -> {
            User mutatedUser = new User();
            mutatedUser.setUsername((String)getCurrentArguments()[0]);
            return mutatedUser;
        });

        replay(testedObject);

        assertThat(testedObject.findOne("TEST").getUsername(), is("TEST"));
    }

    @Test
    public void testMutatedUserConcreate() {
        expect(testedObject.findOne("TEST")).andStubDelegateTo(new UserRepository() {
            @Override
            public User findOne(String login) {
                return testUser();
            }

            @Override
            public void save(User u) {
                return;
            }
        });

        replay(testedObject);

        assertThat(testedObject.findOne("TEST"), is(testUser()));
    }
}
