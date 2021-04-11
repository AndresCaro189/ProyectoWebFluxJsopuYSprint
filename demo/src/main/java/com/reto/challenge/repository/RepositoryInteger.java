package com.reto.challenge.repository;

import com.reto.challenge.model.Comment;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;


public interface RepositoryInteger {

    Flux<Comment> findAll();

    Flux<Comment> mergeMessage();

    Flux<Tuple3<String, String, String>> mergeZip();

    Flux<String> mergezipWith();

    Flux<Integer> mergeconcatWith();

    Flux<Object> forEchToIterable();

//   Flux<Comment> forFlatMap();


}