package com.grupo_5.pub.Config.Async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private static final Logger log = LoggerFactory.getLogger(AsyncConfig.class);

    @Bean(name = "webhookTaskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("webhook-async-");

        executor.setRejectedExecutionHandler((runnable, pool) ->
            log.error("[AsyncConfig] Pool saturado — tarefa rejeitada. " +
                      "Active threads: {}, Queue size: {}",
                      pool.getActiveCount(), pool.getQueue().size())
        );

        executor.initialize();
        log.info("[AsyncConfig] ThreadPoolTaskExecutor inicializado: core={}, max={}, queue={}",
                 executor.getCorePoolSize(), executor.getMaxPoolSize(), executor.getQueueCapacity());

        return executor;
    }
}