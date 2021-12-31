package io.yadnyesh.microservices.demo.twitter.to.kafka.service;

import io.yadnyesh.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@Slf4j
@EnableConfigurationProperties
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;

    public TwitterToKafkaServiceApplication(TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData) {
        this.twitterToKafkaServiceConfigData = twitterToKafkaServiceConfigData;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("App started....");
        log.info(twitterToKafkaServiceConfigData.getTwitterKeywords().toString());
        log.info(twitterToKafkaServiceConfigData.getWelcomeMessage());
    }
}
