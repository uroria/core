package com.uroria.core.localization;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public interface Namespace {

    @NotNull
    Translation getTranslation(@Nullable String key);

    @NotNull
    String getName();

    @NotNull
    Locale getDefaultLocale();

    void setDefaultLocale(@NonNull Locale locale);
}