package ru.easyjava.easymock.service;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;
import ru.easyjava.easymock.entity.User;
import ru.easyjava.easymock.repository.UserRepository;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.easyjava.easymock.service.testObjectsFactory.testGroup;
import static ru.easyjava.easymock.service.testObjectsFactory.testUser;

public class stubPatternMatchingTest {
    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock
    private UserRepository testedObject;

    @Test
    public void testStubTwoVariants() {
        User anotherUser = new User();
        anotherUser.setGroup(testGroup());
        anotherUser.setUsername("SECOND");

        expect(testedObject.findOne(testUser().getUsername())).andStubReturn(testUser());
        expect(testedObject.findOne(anotherUser.getUsername())).andStubReturn(anotherUser);

        replay(testedObject);

        assertThat(testedObject.findOne("SECOND"), is(anotherUser));
        assertThat(testedObject.findOne("TEST"), is(testUser()));
    }

    @Test
    public void testAnyString() {
        expect(testedObject.findOne(anyString())).andStubReturn(testUser());

        replay(testedObject);

        assertThat(testedObject.findOne("SECOND"), is(testUser()));
        assertThat(testedObject.findOne("TEST"), is(testUser()));
    }

    @Test
    public void testStubThreeVariants() {
        User anotherUser = new User();
        anotherUser.setGroup(testGroup());
        anotherUser.setUsername("SECOND");

        expect(testedObject.findOne(testUser().getUsername())).andStubReturn(testUser());
        expect(testedObject.findOne(anotherUser.getUsername())).andStubReturn(anotherUser);
        expect(testedObject.findOne(anyString())).andStubReturn(testUser());

        replay(testedObject);

        assertThat(testedObject.findOne("SECOND"), is(anotherUser));
        assertThat(testedObject.findOne("TEST"), is(testUser()));
        assertThat(testedObject.findOne("STILLALIVE"), is(testUser()));
    }

    @Test
    public void testMatcherCombination() {
        expect(testedObject.findOne(or(startsWith("TE"), matches("S.*")))).andStubReturn(testUser());

        replay(testedObject);

        assertThat(testedObject.findOne("SECOND"), is(testUser()));
        assertThat(testedObject.findOne("TEST"), is(testUser()));
    }
}
