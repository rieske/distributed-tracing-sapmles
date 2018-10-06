package lt.rieske.tracing;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
class MessageListener {

    @RabbitListener(queues = "sampleQueue")
    public void processMessage(String content) {
        System.out.println("received message: " + content);
    }
}
