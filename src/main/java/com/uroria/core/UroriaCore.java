package com.uroria.core;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
public class UroriaCore {
    @Getter
    private final Logger logger = LoggerFactory.getLogger("uroria-core");


}
