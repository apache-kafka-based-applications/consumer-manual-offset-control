package com.trl.consumermanualoffsetcontrol;

import com.trl.consumermanualoffsetcontrol.consumers.Consumer;
import com.trl.consumermanualoffsetcontrol.producers.a.ProducerA;
import com.trl.consumermanualoffsetcontrol.producers.a.ProducerAA;
import com.trl.consumermanualoffsetcontrol.producers.a.ProducerAAA;
import com.trl.consumermanualoffsetcontrol.producers.b.ProducerB;
import com.trl.consumermanualoffsetcontrol.producers.b.ProducerBB;
import com.trl.consumermanualoffsetcontrol.producers.b.ProducerBBB;
import com.trl.consumermanualoffsetcontrol.producers.c.ProducerC;
import com.trl.consumermanualoffsetcontrol.producers.c.ProducerCC;
import com.trl.consumermanualoffsetcontrol.producers.c.ProducerCCC;
import com.trl.consumermanualoffsetcontrol.topics.TopicCreator;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        createTopics();
        Thread.sleep(5000);

        // start consuming topics 't_a', 't_b', 't_c'
        startConsumingFromAllTopics();

        // start the producers to producing to the topic with name 't_a'
        startProducingToTopicTA();

        // start the producers to producing to the topic with name 't_b'
        startProducingToTopicTB();

        // start the producers to producing to the topic with name 't_c'
        startProducingToTopicTC();
    }

    private static void startProducingToTopicTC() {
        new Thread(() -> {
            final ProducerC producerC = new ProducerC();
            producerC.startProducing();
        }).start();

        new Thread(() -> {
            final ProducerCC producerCC = new ProducerCC();
            producerCC.startProducing();
        }).start();

        new Thread(() -> {
            final ProducerCCC producerCCC = new ProducerCCC();
            producerCCC.startProducing();
        }).start();
    }

    private static void startProducingToTopicTB() {
        new Thread(() -> {
            final ProducerB producerB = new ProducerB();
            producerB.startProducing();
        }).start();

        new Thread(() -> {
            final ProducerBB producerBB = new ProducerBB();
            producerBB.startProducing();
        }).start();

        new Thread(() -> {
            final ProducerBBB producerBBB = new ProducerBBB();
            producerBBB.startProducing();
        }).start();
    }

    private static void startProducingToTopicTA() {
        new Thread(() -> {
            final ProducerA producerA = new ProducerA();
            producerA.startProducing();
        }).start();

        new Thread(() -> {
            final ProducerAA producerAA = new ProducerAA();
            producerAA.startProducing();
        }).start();

        new Thread(() -> {
            final ProducerAAA producerAAA = new ProducerAAA();
            producerAAA.startProducing();
        }).start();
    }

    private static void startConsumingFromAllTopics() {
        new Thread(() -> {
            final Consumer consumer = new Consumer();
            consumer.startConsumer();
        }).start();
    }

    private static void createTopics() {
        new Thread(() -> {
            // create topic with name "t_a" and with partition=1 and replicationFactor=1
            TopicCreator.createTopic("t_a", 1, (short) 1);

            // create topic with name "t_b" and with partition=3 and replicationFactor=1
            TopicCreator.createTopic("t_b", 3, (short) 1);

            // create topic with name "t_a" and with partition=7 and replicationFactor=1
            TopicCreator.createTopic("t_c", 7, (short) 1);
        }).start();
    }

}
