package com.reto.challenge.controller;

import com.reto.challenge.repository.ReactiveRepositoryInteger;
import com.reto.challenge.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CommentController.class)
class CommentControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private CommentService service;

    @Test
    void feed() {
        webTestClient.get()
                .uri("/comment/streams")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(article->{
                    byte[] body = article.getResponseBody();
                    Assertions.assertNotEquals(null, body);
                });
    }
    @Test
    void feedCombinetest() {
        webTestClient.get()
                .uri("/comment/feedCombine")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(article->{
                    byte[] body = article.getResponseBody();
                    Assertions.assertNotEquals(null, body);
                });
    }
    @Test
    void margeZipTest() {
        webTestClient.get()
                .uri("/comment/margeZip")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(article->{
                    byte[] body = article.getResponseBody();
                    Assertions.assertNotEquals(null, body);
                });
    }
    @Test
    void margeZipWithTest() {
        webTestClient.get()
                .uri("/comment/margeZipWith")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(article->{
                    byte[] body = article.getResponseBody();
                    Assertions.assertNotEquals(null, body);
                });
    }
    @Test
    void mergeConcatWithTest() {
        webTestClient.get()
                .uri("/comment/mergeConcatWith")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(article->{
                    byte[] body = article.getResponseBody();
                    Assertions.assertNotEquals(null, body);
                });
    }
    @Test
    void forEchToIterableTest() {
        webTestClient.get()
                .uri("/comment/forEchToIterable")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(article->{
                    byte[] body = article.getResponseBody();
                    Assertions.assertNotEquals(null, body);
                });
    }
}