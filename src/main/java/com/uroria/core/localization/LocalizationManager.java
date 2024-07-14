package com.uroria.core.localization;

import com.uroria.core.config.ConfigFile;
import com.uroria.core.option.Option;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class LocalizationManager {
    private final Collection<Namespace> loadedNamespaces = new ObjectArraySet<>();

    public Option<Namespace> createConfigNamespace(String name, ConfigFile provider) {
        if (loadedNamespaces.stream().anyMatch(namespace -> namespace.getName().equals(name))) {
            return Option.none();
        }
        Namespace namespace = new ConfigNamespace(name, provider);
        loadedNamespaces.add(namespace);
        return Option.some(namespace);
    }

    public Option<Namespace> getNamespace(String name) {
        if (name == null) return Option.none();
        return Option.value(loadedNamespaces.stream()
                .filter(ns -> ns.getName().equals(name))
                .findAny().orElse(null));
    }
}
