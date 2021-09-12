package com.trl.consumermanualoffsetcontrol.configs;

import java.util.Properties;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;

public class AdminClientConfig {

    private static final Properties ADMIN_CLIENT_PROPERTIES = new Properties();

    public static Properties getProperties() {
        ADMIN_CLIENT_PROPERTIES.put(BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.getZookeeperServersHost());
        return ADMIN_CLIENT_PROPERTIES;
    }

}
