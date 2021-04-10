package ulas.springreactiverestwebclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@SpringBootApplication
@EnableWebFlux
public class SpringReactiveRestWebclientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringReactiveRestWebclientApplication.class)
                .properties(Collections.singletonMap("server.port", "8090"))
                .run();
    }

    @Bean
    @Qualifier("JsonPlaceholder")
    public WebClient getJsonPlaceholderWebClient(){
        return  WebClient.create("http://jsonplaceholder.typicode.com/" );
    }

    @Bean
    @Qualifier("local")
    public WebClient getJsonPlaceholderWebClient2(){
        return  WebClient.create("http://localhost:8080/" );
    }
}
