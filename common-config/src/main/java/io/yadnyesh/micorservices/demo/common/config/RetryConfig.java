package io.yadnyesh.micorservices.demo.common.config;

import io.yadnyesh.microservices.demo.config.RetryConfigData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfig {

//    private final RetryConfigData retryConfigData;
//
//    public RetryConfig(RetryConfigData retryConfigData) {
//        this.retryConfigData = retryConfigData;
//    }

//    @Bean
//    public RetryTemplate retryTemplate() {
//        RetryTemplate retryTemplate = new RetryTemplate();
//        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
//        exponentialBackOffPolicy.setInitialInterval(retryConfigData.getInitialIntervalMs());
//        exponentialBackOffPolicy.setMaxInterval(retryConfigData.getMaxIntervalMs());
//        exponentialBackOffPolicy.setMultiplier(retryConfigData.getMultiplier());
//        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);
//
//        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
//        simpleRetryPolicy.setMaxAttempts(retryConfigData.getMaxAttempts());
//        retryTemplate.setRetryPolicy(simpleRetryPolicy);
//
//        return  retryTemplate;
//    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(1000);
        exponentialBackOffPolicy.setMaxInterval(10000);
        exponentialBackOffPolicy.setMultiplier(2.0);
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        return  retryTemplate;
    }
}
