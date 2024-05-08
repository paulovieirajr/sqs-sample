package io.github.awsdeveloperslearning.awssqsproducer.service;

import io.awspring.cloud.sqs.SqsException;
import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.util.function.BiConsumer;
import java.util.logging.Logger;

@Service
public class SqsSenderMessage {

    private final static Logger LOGGER = Logger.getLogger(SqsSenderMessage.class.getName());

    @Value("${aws.sqs.queue.name}")
    private String queueName;

    private final SqsAsyncClient sqsAsyncClient;

    public SqsSenderMessage(SqsAsyncClient sqsAsyncClient) {
        this.sqsAsyncClient = sqsAsyncClient;
    }

    public void sendMessage(String message) {
        SqsTemplate.newAsyncTemplate(sqsAsyncClient)
                .sendAsync(queueName, message)
                .whenComplete(treatDeliveryMessage());
    }

    private static BiConsumer<SendResult<String>, Throwable> treatDeliveryMessage() {
        return (result, exception) -> {
            if (result != null) {
                LOGGER.info("Message sent ID: " + result.messageId());
            } else {
                throw new SqsException("Error sending message", exception);
            }
        };
    }
}
