package io.yadnyesh.microservices.demo.twitter.to.kafka.service.init.impl;

import io.yadnyesh.microservices.demo.config.KafkaConfigData;
import io.yadnyesh.microservices.demo.kafka.admin.client.KafkaAdminClient;
import io.yadnyesh.microservices.demo.twitter.to.kafka.service.init.StreamInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class KafkaStreamInitializer implements StreamInitializer {

    private final KafkaConfigData kafkaConfigData;
    private final KafkaAdminClient kafkaAdminClient;

    public KafkaStreamInitializer(KafkaConfigData kafkaConfigData, KafkaAdminClient kafkaAdminClient) {
        this.kafkaConfigData = kafkaConfigData;
        this.kafkaAdminClient = kafkaAdminClient;
    }

    @Override
    public void init() {
        kafkaAdminClient.createTopics();
        kafkaAdminClient.checkSchemaRegistry();
        log.info("****Topics with name {} are ready for operations ", kafkaConfigData.getTopicNamesToCreate().toArray());
    }
}
