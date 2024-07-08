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
    T unwrapOr(@NonNull T value);

    @NotNull
    T unwrapOrGet(@NonNull Supplier<? extends T> supplier);

    @TestOnly
    @Nullable
    T unwrap();
}
