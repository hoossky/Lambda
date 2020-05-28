package com.lambda.web.movie;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity(name="movie")
@Component
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieNo;
    @Column(length = 4, nullable = false)
    private String seq;
    @Column(length = 100, nullable = false)
    private String title;

    public Movie(){}

    public Long getMovieNo() {
        return movieNo;
    }

    public void setMovieNo(Long movieNo) {
        this.movieNo = movieNo;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Builder
    public Movie(String seq, String title){
        this.seq = seq;
        this.title = title;
    }
}
