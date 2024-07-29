package com.uroria.core.result;

import com.uroria.core.option.Option;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

final class OkResult<T, E> implements Result<T, E> {
    private final T value;

    OkResult(T value) {
        this.value = value;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isErr() {
        return false;
    }

    @Override
    public Option<T> ok() {
        return Option.some(this.value);
    }

    @Override
    public Option<E> err() {
        return Option.none();
    }

    @Override
    public void ifOk(@NonNull Consumer<? super T> action) {
        action.accept(this.value);
    }

    @Override
    public void ifErr(@NonNull Consumer<? super E> action) {}

    @Override
    public void ifOkOrErr(@NonNull Consumer<? super T> okAction, @NonNull Consumer<? super E> errAction) {
        okAction.accept(this.value);
    }

    @Override
    public <U> Result<U, E> map(@NonNull Function<? super T, ? extends U> mapper) {
        return Result.ok(mapper.apply(this.value));
    }

    @Override
    public <U> Result<T, U> mapErr(@NonNull Function<? super E, ? extends U> mapper) {
        return Result.ok(this.value);
    }

    @Override
    public <U> Result<U, E> flatMap(@NonNull Function<? super T, ? extends Result<U, E>> mapper) {
        return mapper.apply(this.value);
    }

    @Override
    public <U> Result<T, U> flatMapErr(@NonNull Function<? super E, ? extends Result<T, U>> mapper) {
        return Result.ok(this.value);
    }

    @Override
    public Result<T, E> or(Supplier<? extends Result<? extends T, ? extends E>> supplier) {
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
    public @NotNull E unwrapErrOr(E err) {
        return err;
    }

    @Override
    public @NotNull E unwrapErrOrGet(@NonNull Supplier<? extends E> supplier) {
        return supplier.get();
    }

    @Override
    public @Nullable T unwrap() {
        return this.value;
    }

    @Override
    public @Nullable E unwrapErr() {
        return null;
    }
}
