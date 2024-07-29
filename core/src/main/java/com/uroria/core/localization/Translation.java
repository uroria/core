package com.uroria.core.localization;

import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public interface Translation extends CharSequence {

    Namespace getNamespace();

    String getKey();

    void set(@NonNull Locale locale, @NonNull String content);

    default Component asComponent(TagResolver... resolvers) {
        return asComponent((Locale) null);
    }

    Component asComponent(@Nullable Locale locale, TagResolver... resolvers);

    default String asLegacyString(TagResolver... resolvers) {
        return asLegacyString((Locale) null);
    }

    String asLegacyString(@Nullable Locale locale, TagResolver... resolvers);

    default String asPlainString() {
        return asPlainString(null);
    }

    String asPlainString(@Nullable Locale locale);

    default String asAnsiString(TagResolver... resolvers) {
        return asAnsiString((Locale) null);
    }

    String asAnsiString(@Nullable Locale locale, TagResolver... resolvers);

    default String asJsonString(TagResolver... resolvers) {
        return asJsonString((Locale) null);
    }

    String asJsonString(@Nullable Locale locale, TagResolver... resolvers);
}
