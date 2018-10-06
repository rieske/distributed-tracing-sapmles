package lt.rieske.tracing.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;

@RequiredArgsConstructor
class RabbitClient {

    private final AmqpTemplate amqpTemplate;

    void sendMessage(String payload, String routingKey) {
        var message = new Message(payload.getBytes(),
          MessagePropertiesBuilder.newInstance().build());
        amqpTemplate.send(routingKey, message);
    }
}
