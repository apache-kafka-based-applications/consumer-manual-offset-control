package com.trl.consumermanualoffsetcontrol.configs;

import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

public final class ConsumerConfig {

    private static final Properties CONSUMER_PROPERTIES = new Properties();

    private static final String TOPIC_PATTERN = "t_.+";
    private static final String GROUP_ID = "g1";
    private static final String AUTO_OFFSET = "earliest";

    public static String getTopicPattern() {
        return TOPIC_PATTERN;
    }

    public static Properties getConsumerProperties() {
        CONSUMER_PROPERTIES.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        CONSUMER_PROPERTIES.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        CONSUMER_PROPERTIES.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        CONSUMER_PROPERTIES.setProperty(GROUP_ID_CONFIG, GROUP_ID);
//        CONSUMER_PROPERTIES.setProperty(AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET);
        CONSUMER_PROPERTIES.setProperty(ENABLE_AUTO_COMMIT_CONFIG, "false");
//        CONSUMER_PROPERTIES.setProperty(MAX_POLL_RECORDS_CONFIG, "10");

        return CONSUMER_PROPERTIES;
    }

}
