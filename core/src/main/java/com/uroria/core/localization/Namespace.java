package com.uroria.core.localization;

import lombok.NonNull;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public interface Namespace {

    @NotNull
    Translation getTranslation(@Nullable String key, TagResolver... resolvers);

    @NotNull
    String getName();

    @NotNull
    Locale getDefaultLocale();

    void setDefaultLocale(@NonNull Locale locale);
}
