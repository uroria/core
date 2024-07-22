package com.uroria.core.result;

import com.uroria.core.option.Option;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Result<T, E> {

    static <T, E> Result<T, E> ok(@NonNull T value) {
        return new OkResult<>(value);
    }

    static <T, E> Result<T, E> err(@NonNull E err) {
        return new ErrResult<>(err);
    }

    boolean isOk();

    boolean isErr();

    Option<T> ok();

    Option<E> err();

    void ifOk(@NonNull Consumer<? super T> action);

    void ifErr(@NonNull Consumer<? super E> action);

    void ifOkOrErr(@NonNull Consumer<? super T> okAction, @NonNull Consumer<? super E> errAction);

    <U> Result<U, E> map(@NonNull Function<? super T, ? extends U> mapper);

    <U> Result<T, U> mapErr(@NonNull Function<? super E, ? extends U> mapper);

    <U> Result<U, E> flatMap(@NonNull Function<? super T, ? extends Result<U, E>> mapper);

    <U> Result<T, U> flatMapErr(@NonNull Function<? super E, ? extends Result<T, U>> mapper);

    Result<? extends T, ? extends E> or(Supplier<? extends Result<? extends T, ? extends E>> supplier);

    Stream<T> stream();

    T unwrapOr(@Nullable T value);

    @NotNull
    T unwrapOrGet(@NonNull Supplier<? extends T> supplier);

    E unwrapErrOr(@Nullable E err);

    @NotNull
    E unwrapErrOrGet(@NonNull Supplier<? extends E> supplier);

    /**
     * Returns value T or null.
     */
    @Nullable
    T unwrap();

    /**
     * Returns error E or null.
     */
    @Nullable
    E unwrapErr();
}
