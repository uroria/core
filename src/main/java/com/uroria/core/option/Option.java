package com.uroria.core.option;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Option<T> {

    static <T> Option<T> some(@NonNull T value) {
        return new SomeOption<>(value);
    }

    static <T> Option<T> none() {
        return new NoneOption<>();
    }

    static <T> Option<T> value(@Nullable T value) {
        if (value == null) return none();
        return some(value);
    }

    boolean isSome();

    boolean isNone();

    void ifSome(@NonNull Consumer<? super T> action);

    void ifSomeOrElse(@NonNull Consumer<? super T> action, @NonNull Runnable noneAction);

    Option<T> filter(@NonNull Predicate<? super T> predicate);

    <U> Option<? extends U> map(@NonNull Function<? super T, ? extends U> mapper);

    <U> Option<? extends U> flatMap(@NonNull Function<? super T, ? extends Option<? extends U>> mapper);

    Option<? extends T> or(Supplier<? extends Option<? extends T>> supplier);

    Stream<T> stream();

    @NotNull
    T unwrapOr(T value);

    @NotNull
    T unwrapOrGet(@NonNull Supplier<? extends T> supplier);

    @TestOnly
    @Nullable
    T unwrap();

    @Nullable
    default T unwrapOrNull() {
        return unwrap();
    }
}
