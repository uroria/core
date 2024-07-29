package com.uroria.core.option;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class OptionTest {
    private final String testSome = "Hello, world!";

    @Test
    void some() {
        Option<String> some = Option.some(testSome);
        assert some.isSome();
        assert !some.isNone();
        assertEquals(some.unwrap(), "Hello, world!");
    }

    @Test
    void none() {
        Option<Object> none = Option.none();
        assert none.isNone();
        assert !none.isSome();
        assert none.unwrap() == null;
    }

    @Test
    void ifSomeWhileSome() {
        Option<String> some = Option.some(testSome);
        AtomicReference<String> string = new AtomicReference<>();
        some.ifSome(str -> {
            assertEquals(str, "Hello, world!");
            string.set(str);
        });
        assert string.get() != null;
        assertEquals(string.get(), "Hello, world!");
    }

    @Test
    void ifSomeWhileNone() {
        Option<Object> none = Option.none();
        AtomicBoolean x = new AtomicBoolean(false);
        none.ifSome(obj -> x.set(true));
        assert !x.get();
        assert none.unwrap() == null;
    }

    @Test
    void ifSomeOrElseWhileSome() {
        Option<String> some = Option.some(testSome);
        AtomicReference<String> string = new AtomicReference<>();
        some.ifSomeOrElse(str -> {
            assertEquals(str, "Hello, world!");
            string.set(str);
        }, () -> string.set(null));
        assert string.get() != null;
        assertEquals(string.get(), "Hello, world!");
    }

    @Test
    void ifSomeOrElseWhileNone() {
        Option<Object> none = Option.none();
        AtomicBoolean x = new AtomicBoolean(false);
        none.ifSomeOrElse(obj -> x.set(true), () -> x.set(false));
        assert !x.get();
        assert none.unwrap() == null;
    }

    @Test
    void filterWhileSome() {
        Option<String> some = Option.some(testSome);
        some.filter(string -> string.equals("Hello, world!"));
        assert some.isSome();
        assertEquals(some.unwrap(), "Hello, world!");
    }

    @Test
    void filterWhileNone() {
        Option<String> none = Option.none();
        none.filter(string -> string.equals("Hello, world!"));
        assert none.isNone();
        assert none.unwrap() == null;
    }

    @Test
    void map() {
        Option<String> some = Option.some(testSome);
        Option<? extends String> mapped = some.map(string -> string.replace("!", "."));
        assert !some.equals(mapped);
        assert mapped.isSome();
        assertEquals(some.unwrap(), "Hello, world!");
        assertEquals(mapped.unwrap(), "Hello, world.");
    }

    @Test
    void flatMap() {
        Option<String> some = Option.some(testSome);
        Option<? extends String> mapped = some.flatMap(string -> Option.none());
        assert !some.equals(mapped);
        assert mapped.isNone();
        assertEquals(some.unwrap(), "Hello, world!");
        assert mapped.unwrap() == null;
    }

    @Test
    void or() {
        //todo
    }

    @Test
    void stream() {
        //todo
    }

    @Test
    void unwrapOr() {
        //todo
    }

    @Test
    void unwrapOrGet() {
        //todo
    }

    @Test
    void unwrap() {
        Option<String> some = Option.some(testSome);
        String str = some.unwrap();
        assert str != null;
        assert str.equals("Hello, world!");

        Option<Object> none = Option.none();
        assert none.unwrap() == null;
    }
}