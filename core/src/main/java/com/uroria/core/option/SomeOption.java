package com.uroria.core.option;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;


final class SomeOption<T> implements Option<T> {
    private final T value;

    SomeOption(T value) {
        this.value = value;
    }

    @Override
    public boolean isSome() {
        return true;
    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    public void ifSome(@NonNull Consumer<? super T> action) {
        action.accept(this.value);
    }

    @Override
    public void ifSomeOrElse(@NonNull Consumer<? super T> action, @NonNull Runnable noneAction) {
        action.accept(this.value);
    }

    @Override
    public Option<T> filter(@NonNull Predicate<? super T> predicate) {
        return predicate.test(this.value) ? this : Option.none();
    }

    @Override
    public <U> Option<? extends U> map(@NonNull Function<? super T, ? extends U> mapper) {
        return Option.some(mapper.apply(this.value));
    }

    @Override
    public <U> Option<? extends U> flatMap(@NonNull Function<? super T, ? extends Option<? extends U>> mapper) {
        return mapper.apply(this.value);
    }

    @Override
    public Option<T> or(Supplier<? extends Option<? extends T>> supplier) {
        return this;
    }

    @Override
    public Stream<T> stream() {
        return Stream.of(this.value);
    }

    @Override
    public @NotNull T unwrapOr(T value) {
        return this.value;
    }

    @Override
    public @NotNull T unwrapOrGet(@NonNull Supplier<? extends T> supplier) {
        return this.value;
    }

    @Override
    public T unwrap() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this.value == obj) return true;
        if (!(obj instanceof Option<?> option)) return false;
        return Objects.equals(this.value, option.unwrap());
    }

    @Override
    public String toString() {
        return "Option[" + value + "]";
    }
}
