package lt.rieske.tracing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class MessageListener {

    private final JdbcTemplate jdbcTemplate;

    @KafkaListener(topics = "${spring.kafka.topic}")
    public void listen(ConsumerRecord<?, String> record) {
        log.info("Received message: {}", record);

        jdbcTemplate.update("INSERT INTO sample VALUES(?)", record.value());
    }

}
