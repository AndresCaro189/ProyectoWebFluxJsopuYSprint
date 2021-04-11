package com.reto.challenge.controller;

import com.reto.challenge.model.Comment;
import com.reto.challenge.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;

@RestController
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping(path = "/comment/streams",
        produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Comment> feed() {
        return this.service.list();
    }

    @GetMapping(path = "/comment/feedCombine",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Comment> feedCombine() {
        return this.service.margeList();
    }

    @GetMapping(path = "/comment/margeZip",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple3<String, String, String>> feedZip() {
        return this.service.margeZip();
    }

    @GetMapping(path = "/comment/margeZipWith",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public  Flux<String> feedZipWith() {
        return this.service.mergeZipWithService();
    }

    @GetMapping(path = "/comment/mergeConcatWith",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public  Flux<Integer> feedConcatWith() {
        return this.service.mergeConcatWithService();
    }

    @GetMapping(path = "/comment/forEchToIterable",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> feedEchToIterable() {
        return this.service.echToIterableService();
    }

}