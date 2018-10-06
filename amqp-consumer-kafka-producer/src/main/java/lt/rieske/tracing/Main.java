package lt.rieske.tracing;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
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
    Queue queue() {
        return QueueBuilder.durable("sampleQueue")
          .build();
    }

    @Bean
    public Exchange exchange(@Value("${spring.rabbitmq.template.exchange}") String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName)
          .durable(true)
          .build();
    }

    @Bean
    Binding sampleBinding(Queue sampleQueue, Exchange exchange) {
        return BindingBuilder.bind(sampleQueue).to(exchange).with("test").noargs();
    }
}