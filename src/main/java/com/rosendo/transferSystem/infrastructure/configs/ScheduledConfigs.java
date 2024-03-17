package com.rosendo.transferSystem.infrastructure.configs;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduledConfigs {

    protected final long SECOND = 1000;
    protected final long MINUTE = SECOND * 60;
    protected final long HOUR = MINUTE * 60;
    protected static final String TIME_ZONE = "America/Sao_Paulo";

}
