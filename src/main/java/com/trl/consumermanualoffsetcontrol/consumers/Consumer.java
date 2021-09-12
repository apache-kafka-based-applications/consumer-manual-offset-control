package com.trl.consumermanualoffsetcontrol.consumers;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.trl.consumermanualoffsetcontrol.configs.ConsumerConfig.getConsumerProperties;
import static com.trl.consumermanualoffsetcontrol.configs.ConsumerConfig.getTopicPattern;
import static com.trl.consumermanualoffsetcontrol.constants.Constants.LOG_HEADER;

public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private static final Duration duration = Duration.ofSeconds(5);

    private static String topicName;
    private static Integer partition;

    public void startConsumer() {
        try (final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerProperties())) {
            consumer.subscribe(Pattern.compile(getTopicPattern()));
            LOGGER.info(LOG_HEADER + "Partitions of all topics consumed [{}]", consumer.listTopics());

            while (true) {
                final ConsumerRecords<String, String> records = consumer.poll(duration);

                consumeBatchOfConsumerRecords(records);

                commitBatchOfConsumerRecords(consumer);

                if (records.isEmpty()) {
                    LOGGER.info(LOG_HEADER + ">>> Terminating Consumer <<<");
                    break;
                }

            }
        }
    }

    private static void consumeBatchOfConsumerRecords(final ConsumerRecords<String, String> records) {
        for (final ConsumerRecord<String, String> consumerRecord : records) {
            topicName = consumerRecord.topic();
            partition = consumerRecord.partition();
            final long offset = consumerRecord.offset();
            final String key = consumerRecord.key();
            final String value = consumerRecord.value();

            LOGGER.info(LOG_HEADER + "Consumed: topic = [{}], partition id= [{}], offset = [{}], key = [{}], value = [{}]", topicName, partition, offset, key, value);
        }

    }

    private static void commitBatchOfConsumerRecords(final KafkaConsumer<String, String> consumer) {
        try {
            printInfoAboutOffsetsBeforeCommit(consumer, new TopicPartition(topicName, partition));
//            consumer.commitSync();
            consumer.commitAsync();
            printInfoAboutOffsetsAfterCommit("After  commitAsync() call", consumer, new TopicPartition(topicName, partition));
            LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        } catch (CommitFailedException e) {
            LOGGER.error(LOG_HEADER + "commit failed", e);
        }
    }

    private static void printInfoAboutOffsetsBeforeCommit(final KafkaConsumer<String, String> consumer, final TopicPartition topicPartition) {
        final Map<TopicPartition, OffsetAndMetadata> committed = consumer.committed(new HashSet<>(List.of(topicPartition)));

        final OffsetAndMetadata offsetAndMetadata = committed.get(topicPartition);

        final Long offsetToBeCommitted = offsetAndMetadata == null ? null : offsetAndMetadata.offset();

        // The current position of the consumer (that is, the offset of the next record to be fetched)
        final long currentPositionOfConsumer = consumer.position(topicPartition);

        LOGGER.info(LOG_HEADER + "Offset info before making commit:, offset to be committed: [{}], current position of consumer [{}]", offsetToBeCommitted, currentPositionOfConsumer);
    }

    private static void printInfoAboutOffsetsAfterCommit(final String message, final KafkaConsumer<String, String> consumer, final TopicPartition topicPartition) {
        final Map<TopicPartition, OffsetAndMetadata> committed = consumer.committed(new HashSet<>(List.of(topicPartition)));

        final OffsetAndMetadata offsetAndMetadata = committed.get(topicPartition);

        final Long committedOffset = offsetAndMetadata == null ? null : offsetAndMetadata.offset();
        final long offsetOfTheNextRecordThatWillBeFetched = consumer.position(topicPartition);

        LOGGER.info(LOG_HEADER + "Offset info {}, committed offset: [{}], offset of the next record that will be fetched [{}]", message, committedOffset, offsetOfTheNextRecordThatWillBeFetched);
    }

}
