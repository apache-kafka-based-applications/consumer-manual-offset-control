package com.trl.consumermanualoffsetcontrol.producers.a;

import com.trl.consumermanualoffsetcontrol.configs.ProducerConfig;
import com.trl.consumermanualoffsetcontrol.producers.BasicProducer;

public class ProducerAAA {

    public void startProducing() {
        BasicProducer.startProducing(ProducerConfig.getTopicName_A(), "AAA");
    }

}
