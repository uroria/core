package com.uroria.core.config;

import com.uroria.core.option.Option;
import com.uroria.core.result.Result;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
final class ConfigSectionImpl implements ConfigSection {
    private final FileData data;
    private final String prefix;

    @Override
    public <T> Option<T> get(String key) {
        return data.get(createFinalKey(key));
    }

    @Override
    public boolean contains(String key) {
        return this.data.contains(createFinalKey(key));
    }

    @Override
    public void set(@NonNull String key, @Nullable Object value) {
        this.data.set(createFinalKey(key), value);
    }

    @Override
    public void unset(String key) {
        if (key == null) return;
        this.data.unset(createFinalKey(key));
    }

    @Override
    public Set<String> singleLayerKeySet() {
        return prefix == null || prefix.isEmpty()
                ? data.singleLayerKeySet() : data.singleLayerKeySet(prefix);
    }

    @Override
    public Set<String> singleLayerKeySet(String key) {
        if (key == null) return new ObjectArraySet<>();
        return data.singleLayerKeySet(createFinalKey(key));
    }

    @Override
    public Set<String> keySet() {
        return prefix == null || prefix.isEmpty() ? data.keySet() : data.keySet(prefix);
    }

    @Override
    public Set<String> keySet(String key) {
        if (key == null) return new ObjectArraySet<>();
        return data.keySet(createFinalKey(key));
    }

    @Override
    public ConfigSection getSection(@NotNull String key) {
        return new ConfigSectionImpl(this.data, createFinalKey(key));
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    private String createFinalKey(String key) {
        return prefix == null || prefix.isEmpty() ? key : prefix + "." + key;
    }

    @Override
    public Map<String, Object> getMapping() {
        return this.data.getMapping();
    }

    @Override
    public Result<ConfigProvider, Exception> reload() {
        return this.data.reload();
    }

    @Override
    public Result<ConfigProvider, Exception> save() {
        return this.data.save();
    }
}
