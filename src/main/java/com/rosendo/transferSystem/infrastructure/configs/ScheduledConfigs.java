package com.rosendo.transferSystem.infrastructure.configs;

import com.rosendo.transferSystem.domain.scheduledTransfer.dtos.ScheduledTransferDto;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ScheduledConfigs {

    protected final long SECOND = 1000;
    protected final long MINUTE = SECOND * 60;
    protected final long HOUR = MINUTE * 60;
    protected static final String TIME_ZONE = "America/Sao_Paulo";

    protected LocalDateTime parserDate(ScheduledTransferDto scheduledTransferDto){

        String date = String.valueOf(scheduledTransferDto.timeStamp());

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        return LocalDateTime.parse(date, formatter);
    }

}
