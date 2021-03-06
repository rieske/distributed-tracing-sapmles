package lt.rieske.tracing.rest;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public Exchange exchange(@Value("${spring.rabbitmq.template.exchange}") String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName)
          .durable(true)
          .build();
    }
}