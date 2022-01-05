package io.yadnyesh.microservices.demo.twitter.to.kafka.service;


import io.yadnyesh.microservices.demo.twitter.to.kafka.service.init.StreamInitializer;
import io.yadnyesh.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@EnableConfigurationProperties
@ComponentScan(basePackages = "io.yadnyesh.microservices.demo")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

//    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;

    private final StreamRunner streamRunner;
    private final StreamInitializer streamInitializer;

    public TwitterToKafkaServiceApplication(StreamRunner streamRunner, StreamInitializer streamInitializer) {
        this.streamRunner = streamRunner;
        this.streamInitializer = streamInitializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("App started....");
        streamInitializer.init();
        streamRunner.start();
        log.info("================Starting to listen to the tweets==========");
    }

//    @Override
//    public void run(String... args) throws Exception {
//        log.info("App started....");
//        log.info(twitterToKafkaServiceConfigData.getTwitterKeywords().toString());
//        log.info(twitterToKafkaServiceConfigData.getWelcomeMessage());
//        streamRunner.start();
//        log.info("================Starting to listen to the tweets==========");
//    }
}
