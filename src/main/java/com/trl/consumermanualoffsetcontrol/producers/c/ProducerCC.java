package com.trl.consumermanualoffsetcontrol.producers.c;

import com.trl.consumermanualoffsetcontrol.configs.ProducerConfig;
import com.trl.consumermanualoffsetcontrol.producers.BasicProducer;

public class ProducerCC {

    public void startProducing() {
        BasicProducer.startProducing(ProducerConfig.getTopicName_C(), "CC");
    }

}
