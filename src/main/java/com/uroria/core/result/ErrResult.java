package com.uroria.core.result;

import com.uroria.core.option.Option;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

final class ErrResult<T, E> implements Result<T, E> {
    private final E err;

    ErrResult(E err) {
        this.err = err;
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isErr() {
        return true;
    }

    @Override
    public Option<T> ok() {
        return Option.none();
    }

    @Override
    public Option<E> err() {
        return Option.some(this.err);
    }

    @Override
    public void ifOk(@NonNull Consumer<? super T> action) {}

    @Override
    public void ifErr(@NonNull Consumer<? super E> action) {
        action.accept(this.err);
    }

    @Override
    public void ifOkOrErr(@NonNull Consumer<? super T> okAction, @NonNull Consumer<? super E> errAction) {
        errAction.accept(this.err);
    }

    @Override
    public <U> Result<U, E> map(@NonNull Function<? super T, ? extends U> mapper) {
        return Result.err(this.err);
    }

    @Override
    public <U> Result<T, U> mapErr(@NonNull Function<? super E, ? extends U> mapper) {
        return Result.err(mapper.apply(this.err));
    }

    @Override
    public <U> Result<U, E> flatMap(@NonNull Function<? super T, ? extends Result<U, E>> mapper) {
        return Result.err(this.err);
    }

    @Override
    public <U> Result<T, U> flatMapErr(@NonNull Function<? super E, ? extends Result<T, U>> mapper) {
        return mapper.apply(this.err);
    }

    @Override
    public Result<? extends T, ? extends E> or(Supplier<? extends Result<? extends T, ? extends E>> supplier) {
        return supplier.get();
    }

    @Override
    public Stream<T> stream() {
        return Stream.empty();
    }

    @Override
    public @NotNull T unwrapOr(@NonNull T value) {
        return value;
    }

    @Override
    public @NotNull T unwrapOrGet(@NonNull Supplier<? extends T> supplier) {
        return supplier.get();
    }

    @Override
    public @NotNull E unwrapErrOr(@NonNull E err) {
        return this.err;
    }

    @Override
    public @NotNull E unwrapErrOrGet(@NonNull Supplier<? extends E> supplier) {
        return this.err;
    }

    @Override
    public @Nullable T unwrap() {
        return null;
    }

    @Override
    public @Nullable E unwrapErr() {
        return this.err;
    }
}
