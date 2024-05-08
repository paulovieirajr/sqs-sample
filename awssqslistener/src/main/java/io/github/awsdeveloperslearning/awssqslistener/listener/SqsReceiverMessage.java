package io.github.awsdeveloperslearning.awssqslistener.listener;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SqsReceiverMessage {

    private final static Logger LOGGER = Logger.getLogger(SqsReceiverMessage.class.getName());

    @SqsListener("${aws.sqs.queue.name}")
    public void listen(String message) {
        LOGGER.info("Message received: " + message);
    }
}
