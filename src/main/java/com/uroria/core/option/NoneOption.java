package com.uroria.core.option;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

final class NoneOption<T> implements Option<T> {

    @Override
    public boolean isSome() {
        return false;
    }

    @Override
    public boolean isNone() {
        return true;
    }

    @Override
    public void ifSome(@NotNull Consumer<? super T> action) {}

    @Override
    public void ifSomeOrElse(@NotNull Consumer<? super T> action, @NotNull Runnable noneAction) {
        noneAction.run();
    }

    @Override
    public Option<T> filter(@NonNull Predicate<? super T> predicate) {
        return this;
    }

    @Override
    public <U> Option<U> map(@NonNull Function<? super T, ? extends U> mapper) {
        return Option.none();
    }

    @Override
    public <U> Option<? extends U> flatMap(@NonNull Function<? super T, ? extends Option<? extends U>> mapper) {
        return Option.none();
    }

    @Override
    public Option<? extends T> or(Supplier<? extends Option<? extends T>> supplier) {
        return supplier.get();
    }

    @Override
    public Stream<T> stream() {
        return Stream.empty();
    }

    @Override
    public @NotNull T unwrapOr(T value) {
        return value;
    }

    @Override
    public @NotNull T unwrapOrGet(@NonNull Supplier<? extends T> supplier) {
        return supplier.get();
    }

    @Override
    public @Nullable T unwrap() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(null);
    }

    @Override
    public String toString() {
        return "Option[NONE]";
    }
}
