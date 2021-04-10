package ulas.springreactiverestwebclient.controller;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ulas.springreactiverestwebclient.model.Event;
import ulas.springreactiverestwebclient.model.Photos;
import ulas.springreactiverestwebclient.model.Post;

import java.time.Duration;


@RestController
public class JsonPlaceHolderController {

    final WebClient localClient;
    final WebClient jsonPHClient;

    public JsonPlaceHolderController(@Qualifier("local") WebClient localClient, @Qualifier("JsonPlaceholder")  WebClient jsonPHClient){
        this.localClient=localClient;
        this.jsonPHClient = jsonPHClient;
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<? extends Object> getEvents() {

        return localClient.get()
                .uri("/events/")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchangeToFlux(p -> p.bodyToFlux(Event.class))

                ;

    }


    @GetMapping(value = "/photos", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<? extends Object> getPhotos() {

        return jsonPHClient.get()
                .uri("/photos/")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchangeToFlux(p -> p.bodyToFlux(Photos.class))

                ;

    }

    @GetMapping(value = "/posts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Post>> getPosts() {

        Flux<Post> post = jsonPHClient.get()
                .uri("/posts/")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(Post.class);

        return ResponseEntity.ok(post);
    }

    @GetMapping(value = "/posts/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Mono<Post>> getPostById(@PathVariable long id) {

        Mono<Post> post = jsonPHClient.get()
                .uri("/posts/" + id)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToMono(Post.class);

        return ResponseEntity.ok(post);

    }
}
