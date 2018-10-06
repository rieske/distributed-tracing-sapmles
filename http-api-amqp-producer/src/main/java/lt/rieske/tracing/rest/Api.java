package lt.rieske.tracing.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
class Api {

    @Bean
    RouterFunction<ServerResponse> v1Api(
      AmqpTemplate amqpTemplate) {
        var handler = new ApiV1Handler(new RabbitClient(amqpTemplate));

        return
          route(GET("/api/v1/sample"), handler::getSample)
            .and(route(POST("/api/v1/sample"), handler::postToRabbit));
    }

    @RequiredArgsConstructor
    private static class ApiV1Handler {

        private final RabbitClient rabbitClient;

        Mono<ServerResponse> getSample(ServerRequest request) {
            return ok().body(Mono.just("{\"foo\":\"bar\"}"), String.class);
        }

        Mono<ServerResponse> postToRabbit(ServerRequest request) {

            rabbitClient.sendMessage("{}", "test");

            return noContent().build();
        }
    }
}
