package ulas.springreactiveserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import ulas.springreactiverestwebclient.model.Event;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class ServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class, args);
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> getEvents() {
        Flux<Event> eventFlux = Flux.fromStream(Stream.generate(() -> new Event(System.currentTimeMillis(), LocalDateTime.now())));
        return Flux.zip(eventFlux, Flux.interval(Duration.ofSeconds(1))).map(Tuple2::getT1);
    }
}
