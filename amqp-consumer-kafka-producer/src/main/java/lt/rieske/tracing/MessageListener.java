package lt.rieske.tracing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class MessageListener {

    @RabbitListener(queues = "sampleQueue")
    public void processMessage(byte[] content) {
        log.info("Received message: {}", new String(content));
    }
}
