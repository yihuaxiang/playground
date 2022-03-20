package com.fudongdong.website.job;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author dongdong.fdd
 * @date 2022/3/20 19:55
 */
@Component
@Slf4j
public class DemoJob {
    @Scheduled(fixedRate = 3000)
    public void scheduledTask() {
        log.info("任务执行时间：{}", LocalDateTime.now());
    }
}
