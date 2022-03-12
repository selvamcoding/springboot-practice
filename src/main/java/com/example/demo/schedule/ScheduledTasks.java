package com.example.demo.schedule;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    private MeterRegistry meterRegistry;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 * * * * *")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        meterRegistry.gauge("scheduled_value", 1);
        meterRegistry.timer("scheduled_time");
    }
}
