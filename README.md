# SQS - Producer and Listener

There are two projects in this repository to show how SQS works with Spring.

## Listener

The listener is another Spring Boot application that listens to the SQS queue and logs the messages in the console. To run the listener, you need to set the environment variables `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY` with your AWS credentials.

On Mac or Linux, you can use the following command to set the environment variables:
```bash
export AWS_ACCESS_KEY_ID=your_access_key_id
export AWS_SECRET_ACCESS_KEY=your_secret_access_key
```

On Windows, you can use the following command to set the environment variables:
```cmd
set AWS_ACCESS_KEY_ID=your_access_key_id
set AWS_SECRET_ACCESS_KEY=your_secret_access_key
```

There are kind of order the AWS SDK following to get the credentials. You can check the order in the following link: [AWS SDK for Java - Default Credential Provider Chain](https://docs.awspring.io/spring-cloud-aws/docs/3.1.0/reference/html/index.html#defaultcredentialsprovider)

After that, you will be able to run the listener application. Note on AWS Console that queue will be created automatically when the listener application starts.

If you prefer, you can use AWS CLI to check the queue. Just run the following command:

```bash
aws sqs list-queues
```

Something like that will prompt in your terminal:

```json
{
  "QueueUrls": [
    "https://sqs.us-east-1.amazonaws.com/<ACCOUNT_ID>/sqs-sample-queue"
  ]
}
```

## Producer

The producer is a Spring Boot application that sends messages to a SQS queue. In this project specifically, we send a message through a POST request to the endpoint `/send`.

For simple demonstration purposes, you can use Postman, Insomnia or any other tool like HTTPie to send a POST request to the endpoint. I will use HTTPie in this example. You can download it in the following link: [HTTPie](https://httpie.io/)

```bash
http POST localhost:8080/send message="Hello, World!"
```

This command will send a message to the SQS queue and print some logs in the console.

After that, check the logs on the listener application. You will see the message that was sent by the producer.