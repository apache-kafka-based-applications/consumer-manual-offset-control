package com.trl.consumermanualoffsetcontrol.configs;

public class KafkaConfig {

    private static final String BOOTSTRAP_SERVERS_HOST = "localhost:9092";
    private static final String ZOOKEEPER_SERVERS_HOST = "localhost:2181";

    public static String getBootstrapServer() {
        return BOOTSTRAP_SERVERS_HOST;
    }

    public static String getZookeeperServersHost() {
        return BOOTSTRAP_SERVERS_HOST;
    }

}
