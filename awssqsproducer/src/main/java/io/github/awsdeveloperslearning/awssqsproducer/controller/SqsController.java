package io.github.awsdeveloperslearning.awssqsproducer.controller;

import io.awspring.cloud.sqs.SqsException;
import io.github.awsdeveloperslearning.awssqsproducer.service.SqsSenderMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.logging.Level.INFO;
import java.util.logging.Logger;


@RestController
@RequestMapping("/send")
public class SqsController {

    private final static Logger LOGGER = Logger.getLogger(SqsController.class.getName());
    private final SqsSenderMessage sqsSenderMessage;

    public SqsController(SqsSenderMessage sqsSenderMessage) {
        this.sqsSenderMessage = sqsSenderMessage;
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String message) {
        LOGGER.info("Endpoint /send called");
        try {
            sqsSenderMessage.sendMessage(message);
            return ResponseEntity.ok().build();
        } catch (SqsException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
