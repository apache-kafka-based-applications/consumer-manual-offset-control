package com.trl.consumermanualoffsetcontrol.topics;

import com.trl.consumermanualoffsetcontrol.configs.AdminClientConfig;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.trl.consumermanualoffsetcontrol.constants.Constants.LOG_HEADER;

public final class TopicCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicCreator.class);

    private TopicCreator() {
    }

    public static void createTopic(final String topicName, final int numPartitions, short replicationFactor) {
        try (final AdminClient admin = AdminClient.create(AdminClientConfig.getProperties())) {

            //checking if topic already exists
            final ArrayList<String> listOfTopics = new ArrayList<>(admin.listTopics().names().get());
            LOGGER.info(LOG_HEADER + "List of topics [{}]", listOfTopics);

            boolean alreadyExists = listOfTopics.stream().anyMatch(existingTopicName -> existingTopicName.equals(topicName));

            if (alreadyExists) {
                LOGGER.info(LOG_HEADER + "Topic already exits: [{}]", topicName);
            } else {
                //creating new topic
                LOGGER.info(LOG_HEADER + "Creating topic: [{}]", topicName);
                NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);
                admin.createTopics(Collections.singleton(newTopic)).all().get();
                Thread.sleep(1000);
            }

            //describing
            LOGGER.info(LOG_HEADER + "-- describing topic --");
            admin.describeTopics(Collections.singleton(topicName)).all().get()
                    .forEach((topic, desc) -> {
                        LOGGER.info(LOG_HEADER + "Topic: [{}]", topic);
                        LOGGER.info(LOG_HEADER + "Partitions: [{}], partition ids: [{}]", desc.partitions().size(),
                                desc.partitions()
                                        .stream()
                                        .map(p -> Integer.toString(p.partition()))
                                        .collect(Collectors.joining(",")));
                    });

        } catch (Exception e) {
            final String exceptionMessage = "Some kind of exception occur during creation of topic.";
            LOGGER.error(exceptionMessage, e);
        }
    }

}
