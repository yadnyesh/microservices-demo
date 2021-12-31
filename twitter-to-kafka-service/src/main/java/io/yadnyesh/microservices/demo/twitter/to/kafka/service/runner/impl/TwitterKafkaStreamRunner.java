package io.yadnyesh.microservices.demo.twitter.to.kafka.service.runner.impl;

import io.yadnyesh.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import io.yadnyesh.microservices.demo.twitter.to.kafka.service.listener.TwitterKafkaStatusListener;
import io.yadnyesh.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
@Slf4j
public class TwitterKafkaStreamRunner implements StreamRunner {
    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;

    private final TwitterKafkaStatusListener twitterKafkaStatusListener;

    private TwitterStream twitterStream;

    public TwitterKafkaStreamRunner(TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData, TwitterKafkaStatusListener twitterKafkaStatusListener) {
        this.twitterToKafkaServiceConfigData = twitterToKafkaServiceConfigData;
        this.twitterKafkaStatusListener = twitterKafkaStatusListener;
    }

    @Override
    public void start() throws TwitterException {
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterKafkaStatusListener);
        addFilter();
    }

    private void addFilter() {
        String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        log.info("Started filtering twitter stream  for keywords {}", Arrays.toString(keywords));
    }

    @PreDestroy
    public void shutdown() {
        if(twitterStream != null) {
            log.info("Closing twitter stream!");
            twitterStream.shutdown();
        }
    }
}
