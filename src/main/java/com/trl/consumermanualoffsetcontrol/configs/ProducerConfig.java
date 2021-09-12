package com.trl.consumermanualoffsetcontrol.configs;

import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

public final class ProducerConfig {

    private static final String TOPIC_A_NAME = "t_a";
    private static final String TOPIC_B_NAME = "t_b";
    private static final String TOPIC_C_NAME = "t_c";

    private static final Properties PRODUCER_PROPERTIES = new Properties();

    public static String getTopicName_A() {
        return TOPIC_A_NAME;
    }

    public static String getTopicName_B() {
        return TOPIC_B_NAME;
    }

    public static String getTopicName_C() {
        return TOPIC_C_NAME;
    }

    public static Properties getProducerProperties() {
        PRODUCER_PROPERTIES.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        PRODUCER_PROPERTIES.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        PRODUCER_PROPERTIES.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return PRODUCER_PROPERTIES;
    }

}
