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
    public @NotNull T unwrapOr(@NonNull T value) {
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
