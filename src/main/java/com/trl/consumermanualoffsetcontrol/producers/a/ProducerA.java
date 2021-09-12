package com.trl.consumermanualoffsetcontrol.producers.a;

import com.trl.consumermanualoffsetcontrol.producers.BasicProducer;

import static com.trl.consumermanualoffsetcontrol.configs.ProducerConfig.getTopicName_A;

public class ProducerA {

    public void startProducing() {
        BasicProducer.startProducing(getTopicName_A(), "A");
    }

}
