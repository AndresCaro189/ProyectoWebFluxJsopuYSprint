package com.reto.challenge.repository;

import com.reto.challenge.model.Comment;
import com.reto.challenge.util.Scraping;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ReactiveRepositoryInteger implements RepositoryInteger {

    private List<Comment> generateComment(long interval) {
        Scraping.getStatusConnectionCode();
        Comment obj = new Comment(
                Scraping.author(),
                Scraping.message(),
                Scraping.timeStamp());
        return  Arrays.asList(obj);
    }

    @Override
    public Flux<Comment> findAll() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(10)
                .onBackpressureDrop()
                .map(this::generateComment)
                .flatMapIterable(x -> x);
    }

    @Override
    public Flux<Comment> mergeMessage() {
        List<Comment> commentlist =new ArrayList<>();
        commentlist.add(new Comment("Jhon ", "El segundo Mejor curso del mundo", "2020-01-22"));
        commentlist.add(new Comment("David ", "El tercer Mejor curso del mundo", "2020-02-22"));
        commentlist.add(new Comment("Leo ", "El cuarto Mejor curso del mundo", "2020-03-22"));

        return Flux.fromIterable(commentlist).map(c->{
        c.setMessage(c.getMessage()+ " Oferta especial");
           return c;
        });
    }

    @Override
    public Flux<Tuple3<String, String, String>> mergeZip() {
        Flux<String> authorTitle = Flux.just("Andres", "Laura", "Karen");
        Flux<String> courseTitle = Flux.just("como programar a los 5 años", "como programar a los 6 años", "como programar a los 7 años");
        Flux<String> timeOfPublication = Flux.just("2020-01-02",  "2020-03-02", "2020-06-02" );

         return Flux.zip(authorTitle,courseTitle,timeOfPublication);
    }

    @Override
    public Flux<String> mergezipWith() {
        List<Comment> list1 = new ArrayList<>();
        list1.add(new Comment("Andres", "como programar a los 5 años", "2020-01-02"));
        list1.add(new Comment("Laura", "como programar a los 6 años", "2020-03-02"));

        List<Comment> list2 = new ArrayList<>();
        list2.add(new Comment("Karen", "como programar a los 7 años", "2020-06-02"));
        list2.add(new Comment("Brayan", "como programar a los 8 años", "2020-09-02"));

        Flux<Comment> fx1 =Flux.fromIterable(list1);
        Flux<Comment> fx2 =Flux.fromIterable(list2);

        return fx1.zipWith(fx2, (a, b) -> String.format("Flux1: %s, Flux2: %s", a, b));
    }

    @Override
    public Flux<Integer> mergeconcatWith() {
        Flux<Integer> evenNumbers = getIntegerFlux1();
        Flux<Integer> oddNumbers = getIntegerFlux2();
        return evenNumbers.concatWith(oddNumbers);
    }

    private Flux<Integer> getIntegerFlux1() {
        Flux<Integer> oddNumbers = Flux
                .range(0, 10)
                .filter(x -> x % 2 > 0);
        return oddNumbers;
    }

    private Flux<Integer> getIntegerFlux2() {
        Flux<Integer> evenNumbers = Flux
                .range(0, 10)
                .filter(x -> x % 2 == 0);
        return evenNumbers;
    }

    @Override
    public Flux<Object> forEchToIterable() {
        return Flux.empty().switchIfEmpty(Flux.just("No es una entidad Error"));
    }

//    @Override
//    public Flux<Comment> forFlatMap() {
//        Comment comment1= new Comment("Andres", "como programar a los 5 años", "2020-01-02");
//        Comment comment2= new Comment("Brayan", "como programar a los 8 años", "2020-09-02");
//
//        Course course1= new Course("Html");
//        Course course2= new Course("Java");
//        Course course3= new Course("Css");
//        Course course4= new Course("JS");
//
//        comment1.getCourseComment(course1);
//        comment1.getCourseComment(course2);
//        comment2.getCourseComment(course3);
//        comment2.getCourseComment(course4);
//
//        List<Comment> lista= new ArrayList<Comment>();
//        lista.add(comment1);
//        lista.add(comment2);
//
//        return lista.stream().map(comment->comment.getCourseComment())
//                .flatMap(courses -> courses.)
//                .map(course->course.getClass());
//
//    }


}