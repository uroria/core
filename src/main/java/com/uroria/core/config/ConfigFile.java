package com.uroria.core.config;

import com.moandjiezana.toml.Toml;
import com.uroria.core.option.Option;
import com.uroria.core.result.Result;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ConfigFile extends ConfigProvider {

    static Result<ConfigFile, Exception> toml(String path) {
        return toml(new File(path));
    }

    static Result<ConfigFile, Exception> toml(@NonNull File file) {
        try {
            TomlConfig config = new TomlConfig(file);
            return config.reload().map(cfg -> config.getData());
        } catch (Exception exception) {
            return Result.err(exception);
        }
    }

    static Result<ConfigFile, Exception> toml(@NonNull InputStream stream) {
        try {
            TomlConfig config = new TomlConfig(new Toml().read(stream));
            return config.reload().map(cfg -> config.getData());
        } catch (Exception exception) {
            return Result.err(exception);
        }
    }

    <T> Option<T> get(String key);

    boolean contains(String key);

    void set(@NonNull String key, @Nullable Object value);

    void unset(String key);

    default void remove(String key) {
        unset(key);
    }

    Set<String> singleLayerKeySet();

    Set<String> singleLayerKeySet(String key);

    Set<String> keySet();

    Set<String> keySet(String key);

    ConfigSection getSection(@NonNull String key);

    default <T> T getOrDefault(String key, @NonNull T defaultValue) {
        Object obj = get(key).unwrapOr(defaultValue);
        return ClassWrapper.getFromDef(obj, defaultValue);
    }

    default <T> T getOrSetDefault(@NonNull String key, @NonNull T defaultValue) {
        Option<Object> option = get(key);
        if (option.isNone()) {
            set(key, defaultValue);
            return defaultValue;
        }
        Object obj = option.unwrapOr(defaultValue);
        return ClassWrapper.getFromDef(obj, defaultValue);
    }

    default String getString(String key) {
        return getOrDefault(key, "");
    }

    default int getInt(String key) {
        return getOrDefault(key, 0);
    }

    default long getLong(String key) {
        return getOrDefault(key, 0L);
    }

    default byte getByte(String key) {
        return getOrDefault(key, (byte) 0);
    }

    default boolean getBoolean(String key) {
        return getOrDefault(key, false);
    }

    default float getFloat(String key) {
        return getOrDefault(key, 0f);
    }

    default double getDouble(String key) {
        return getOrDefault(key, 0d);
    }

    default <T> List<T> getList(String key) {
        return getOrDefault(key, new ObjectArrayList<>());
    }

    default <K, V> Map<K, V> getMap(String key) {
        return getOrDefault(key, new Object2ObjectArrayMap<>());
    }

    default void setDefault(@NonNull String key, Object value) {
        if (contains(key)) return;
        set(key, value);
    }
}
