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
    public <U> Result<? extends U, E> map(@NonNull Function<? super T, ? extends U> mapper) {
        return Result.ok(mapper.apply(this.value));
    }

    @Override
    public <U> Result<T, ? extends U> mapErr(@NonNull Function<? super E, ? extends U> mapper) {
        return Result.ok(this.value);
    }

    @Override
    public <U> Result<? extends U, E> flatMap(@NonNull Function<? super T, ? extends Result<? extends U, E>> mapper) {
        return mapper.apply(this.value);
    }

    @Override
    public <U> Result<T, ? extends U> flatMapErr(@NonNull Function<? super E, ? extends Result<T, ? extends U>> mapper) {
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
    public @NotNull T unwrapOr(@NonNull T value) {
        return this.value;
    }

    @Override
    public @NotNull T unwrapOrGet(@NonNull Supplier<? extends T> supplier) {
        return this.value;
    }

    @Override
    public @NotNull E unwrapErrOr(@NonNull E err) {
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
