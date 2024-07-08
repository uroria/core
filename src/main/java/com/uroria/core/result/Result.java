/*
    Copyright 2024 Uroria

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

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

    <U> Result<? extends U, E> map(@NonNull Function<? super T, ? extends U> mapper);

    <U> Result<T, ? extends U> mapErr(@NonNull Function<? super E, ? extends U> mapper);

    <U> Result<? extends U, E> flatMap(@NonNull Function<? super T, ? extends Result<? extends U, E>> mapper);

    <U> Result<T, ? extends U> flatMapErr(@NonNull Function<? super E, ? extends Result<T, ? extends U>> mapper);

    Result<? extends T, ? extends E> or(Supplier<? extends Result<? extends T, ? extends E>> supplier);

    Stream<T> stream();

    @NotNull
    T unwrapOr(@NonNull T value);

    @NotNull
    T unwrapOrGet(@NonNull Supplier<? extends T> supplier);

    @NotNull
    E unwrapErrOr(@NonNull E err);

    @NotNull
    E unwrapErrOrGet(@NonNull Supplier<? extends E> supplier);

    /**
     * Returns value T or null.
     * Made for testing purposes only, since
     * not null-safe!
     */
    @TestOnly
    @Nullable
    T unwrap();

    /**
     * Returns error E or null.
     * Made for testing purposes only, since
     * not null-safe!
     */
    @TestOnly
    @Nullable
    E unwrapErr();
}
