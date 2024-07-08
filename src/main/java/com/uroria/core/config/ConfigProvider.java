package com.uroria.core.config;

import com.uroria.core.result.Result;

import java.util.Map;

public interface ConfigProvider {

    Map<String, Object> getMapping();

    Result<ConfigProvider, Exception> reload();

    Result<ConfigProvider, Exception> save();
}
