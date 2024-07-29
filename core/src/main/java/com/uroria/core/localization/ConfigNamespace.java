package com.uroria.core.localization;

import com.uroria.core.config.ConfigFile;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.ansi.ANSIComponentSerializer;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
final class ConfigNamespace implements Namespace {
    private final String name;
    private final ConfigFile config;
    private final Map<String, Translation> translationCache = new Object2ObjectArrayMap<>();

    private Namespace getSelf() {
        return this;
    }

    @Override
    public @NotNull Translation getTranslation(@Nullable String key, TagResolver... parentResolvers) {
        if (key == null) return getTranslation("unknown");
        Translation translation = translationCache.get(key);
        if (translation != null) return translation;

        Translation freshTranslation = new Translation() {
            private final Map<Locale, String> cache = new Object2ObjectArrayMap<>();
            private final String asString = asLegacyString();

            @Override
            public Namespace getNamespace() {
                return getSelf();
            }

            @Override
            public String getKey() {
                return key;
            }

            @Override
            public void set(@NonNull Locale locale, @NonNull String content) {
                config.set(key + "." + locale.toLanguageTag(), content);
            }

            @Override
            public Component asComponent(@Nullable Locale locale, TagResolver... resolvers) {
                Set<TagResolver> resolverSet = new ObjectArraySet<>(resolvers);
                resolverSet.addAll(Arrays.asList(parentResolvers));
                return MiniMessage.miniMessage().deserialize(asPlainString(locale), resolvers);
            }

            @Override
            public String asLegacyString(@Nullable Locale locale, TagResolver... resolvers) {
                return LegacyComponentSerializer.legacySection().serialize(asComponent(locale, resolvers));
            }

            @Override
            public String asPlainString(@Nullable Locale locale) {
                if (locale == null) return asPlainString(getDefaultLocale());
                String translation = cache.get(locale);
                if (translation != null) return translation;
                String fullKey = key + "." + locale.toLanguageTag();
                String freshTranslation = config.getOrSetDefault(fullKey, fullKey);
                cache.put(locale, freshTranslation);
                return freshTranslation;
            }

            @Override
            public String asAnsiString(@Nullable Locale locale, TagResolver... resolvers) {
                return ANSIComponentSerializer.ansi().serialize(asComponent(locale));
            }

            @Override
            public String asJsonString(@Nullable Locale locale, TagResolver... resolvers) {
                return GsonComponentSerializer.gson().serialize(asComponent(locale, resolvers));
            }

            @Override
            public int length() {
                return asString.length();
            }

            @Override
            public char charAt(int i) {
                return asString.charAt(i);
            }

            @NotNull
            @Override
            public CharSequence subSequence(int i, int i1) {
                return asString.subSequence(i, i1);
            }
        };

        translationCache.put(key, freshTranslation);
        return freshTranslation;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull Locale getDefaultLocale() {
        return this.config.getOrSetDefault("defaultLocale", Locale.ENGLISH);
    }

    @Override
    public void setDefaultLocale(@NonNull Locale locale) {
        this.config.set("defaultLocale", locale);
    }
}
