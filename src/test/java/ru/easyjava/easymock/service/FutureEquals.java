package ru.easyjava.easymock.service;

import org.easymock.EasyMock;
import org.easymock.IArgumentMatcher;

import java.time.LocalDate;

public class FutureEquals implements IArgumentMatcher {
    private final long daysInFuture;

    public FutureEquals(final long d) {
        this.daysInFuture = d;
    }

    @Override
    public boolean matches(Object argument) {
        if (!(argument instanceof LocalDate)) {
            return false;
        }

        LocalDate actual = (LocalDate) argument;
        return LocalDate.now().plusDays(daysInFuture).getDayOfYear()==actual.getDayOfYear();
    }

    @Override
    public void appendTo(StringBuffer buffer) {
        buffer.append("eqFuture(");
        buffer.append(LocalDate.now().plusDays(daysInFuture));
        buffer.append(")");
    }

    public static LocalDate eqFuture(long expected) {
        EasyMock.reportMatcher(new FutureEquals(expected));
        return null;
    }
}
