package lt.rieske.tracing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class MessageListener {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @RabbitListener(queues = "sampleQueue")
    void processMessage(byte[] content) {
        var contentStr = new String(content);
        log.info("Received message: {}", new String(contentStr));

        kafkaTemplate.send("sample-topic", "some-key", contentStr);
    }
}
