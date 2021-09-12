package com.trl.consumermanualoffsetcontrol.producers.b;

import com.trl.consumermanualoffsetcontrol.configs.ProducerConfig;
import com.trl.consumermanualoffsetcontrol.producers.BasicProducer;

public class ProducerB {

    public void startProducing() {
        BasicProducer.startProducing(ProducerConfig.getTopicName_B(), "B");
    }

}
