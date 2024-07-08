package com.uroria.core.config;

import com.uroria.core.option.Option;
import com.uroria.core.result.Result;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
final class FileData implements ConfigFile {
    private final ConfigProvider provider;
    private final Map<String, Object> mapping;
    private String prefix;

    FileData(ConfigProvider provider) {
        this.provider = provider;
        this.mapping = provider.getMapping();
    }

    @Override
    public <T> Option<T> get(String key) {
        if (key == null) return Option.none();
        key = this.prefix == null ? key : this.prefix + "." + key;
        return get(this.mapping, key.split("\\."), 0);
    }

    @SuppressWarnings("unchecked")
    private <T> Option<T> get(Map<String, Object> map, String[] key, int id) {
        if (id < key.length - 1) {
            if (map.get(key[id]) instanceof Map) {
                Map<String, Object> tempMap = (Map<String, Object>) map.get(key[id]);
                return get(tempMap, key, id + 1);
            }
            return Option.none();
        }
        return Option.value((T) map.get(key[id]));
    }

    @Override
    public boolean contains(String key) {
        return get(key).isSome();
    }

    @Override
    public void set(@NonNull String key, @Nullable Object value) {
        this.mapping.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.mapping.remove(key);
    }

    @Override
    public Set<String> singleLayerKeySet() {
        return this.mapping.keySet();
    }

    @Override
    public Set<String> singleLayerKeySet(String key) {
        Object obj = get(key).unwrapOrNull();
        return get(key) instanceof Map map ? ((Map<String, Object>) map).keySet() : new ObjectArraySet<>();
    }

    @Override
    public Set<String> keySet() {
        return multiLayerKeySet(mapping);
    }

    @Override
    public Set<String> keySet(String key) {
        Object obj = get(key).unwrapOrNull();
        return obj instanceof Map map ? multiLayerKeySet((Map<String, Object>) map) : new ObjectArraySet<>();
    }

    @SuppressWarnings("unchecked")
    private Set<String> multiLayerKeySet(Map<String, Object> map) {
        Set<String> out = new ObjectArraySet<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!(entry.getValue() instanceof Map)) {
                out.add(entry.getKey());
                continue;
            }
            for (String tempKey : multiLayerKeySet((Map<String, Object>) entry.getValue())) {
                out.add(entry.getKey() + "." + tempKey);
            }
        }
        return out;
    }

    @Override
    public ConfigSection getSection(@NotNull String key) {
        return new ConfigSectionImpl(this, key);
    }

    @Override
    public Map<String, Object> getMapping() {
        return new Object2ObjectLinkedOpenHashMap<>(this.mapping);
    }

    @Override
    public Result<ConfigProvider, Exception> reload() {
        return this.provider.reload().map(ignored -> this);
    }

    @Override
    public Result<ConfigProvider, Exception> save() {
        return this.provider.save().map(ignored -> this);
    }
}
