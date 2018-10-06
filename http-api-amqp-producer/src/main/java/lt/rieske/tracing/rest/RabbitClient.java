package lt.rieske.tracing.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;

@Slf4j
@RequiredArgsConstructor
class RabbitClient {

    private final AmqpTemplate amqpTemplate;

    void sendMessage(String payload, String routingKey) {
        log.info("Sending {} to rabbit with key {}", payload, routingKey);

        var message = new Message(payload.getBytes(),
          MessagePropertiesBuilder.newInstance().build());
        amqpTemplate.send(routingKey, message);
    }
}
