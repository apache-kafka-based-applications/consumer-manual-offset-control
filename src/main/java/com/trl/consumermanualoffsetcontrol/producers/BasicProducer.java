package com.trl.consumermanualoffsetcontrol.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.trl.consumermanualoffsetcontrol.configs.ProducerConfig.getProducerProperties;

public final class BasicProducer {

    private BasicProducer() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicProducer.class);

    public static void startProducing(final String topicName, final String key) {
        try (final KafkaProducer<String, String> producer = new KafkaProducer<>(getProducerProperties())) {
            ProducerRecord<String, String> producerRecord;

            for (int i = 0; i < 1000; i++) {
                final String value = i + "";

                producerRecord = new ProducerRecord<>(topicName, key, value);
                producer.send(producerRecord);
                producer.flush();
//                LOGGER.info(LOG_HEADER + "Event is produced. Event info -> topic: [{}] key: [{}] value: [{}]", topicName, key, value);

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            final String exceptionMessage = "Some kind of exception occur during producing event.";
            LOGGER.error(exceptionMessage, e);
        }

    }

}
