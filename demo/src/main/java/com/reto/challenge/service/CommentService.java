package com.reto.challenge.service;

import com.reto.challenge.model.Comment;
import com.reto.challenge.repository.RepositoryInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;


@Service
public class CommentService {

    @Autowired
    private RepositoryInteger repository;

    public Flux<Comment> list(){ return repository.findAll();}

    public Flux<Comment> margeList(){ return repository.mergeMessage();}

    public Flux<Tuple3<String, String, String>> margeZip(){return repository.mergeZip();}

    public Flux<String> mergeZipWithService(){return repository.mergezipWith();}

    public Flux<Integer> mergeConcatWithService(){return  repository.mergeconcatWith();}

    public Flux<Object> echToIterableService(){return repository.forEchToIterable();}
}
