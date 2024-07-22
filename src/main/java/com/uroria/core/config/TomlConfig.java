package com.uroria.core.config;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import com.uroria.core.result.Result;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Map;

@Getter
final class TomlConfig implements ConfigProvider {
    private Toml toml;
    @Nullable
    private final File file;
    private final FileData data;
    private final Map<String, Object> mapping;

    TomlConfig(@NonNull File file) {
        this(file, new Toml().read(file));
    }

    TomlConfig(Toml toml) {
        this(null, toml);
    }

    TomlConfig(@Nullable File file, Toml toml) {
        this.toml = toml;
        this.file = file;
        this.mapping = new Object2ObjectLinkedOpenHashMap<>();
        this.data = new FileData(this);
    }

    @Override
    public Map<String, Object> getMapping() {
        return this.mapping;
    }

    @Override
    public Result<ConfigProvider, Exception> reload() {
        try {
            if (file == null) return Result.ok(this);
            if (!file.exists()) return save();
            this.mapping.clear();
            this.toml = toml.read(this.file);
            Map<String, Object> map = toml.toMap();
            this.mapping.putAll(map);
            return Result.ok(this);
        } catch (Exception exception) {
            return Result.err(exception);
        }
    }

    @Override
    public Result<ConfigProvider, Exception> save() {
        try {
            if (file == null) return Result.ok(this);
            TomlWriter writer = new TomlWriter();
            writer.write(this.mapping, this.file);
            return Result.ok(this);
        } catch (Exception exception) {
            return Result.err(exception);
        }
    }
}
