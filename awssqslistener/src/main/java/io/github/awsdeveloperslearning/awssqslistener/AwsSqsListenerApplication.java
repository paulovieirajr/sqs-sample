package io.github.awsdeveloperslearning.awssqslistener;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsSqsListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSqsListenerApplication.class, args);
	}
}
