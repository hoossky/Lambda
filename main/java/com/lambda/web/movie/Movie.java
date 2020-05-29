package com.lambda.web.movie;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieSeq;
    @Column(length = 4)
    private String rank;
    @Column(length = 100)
    private String title;
    @Column(length = 10)
    private String rankDate;

    public Movie(){}

    public Long getMovieSeq() {
        return movieSeq;
    }

    public String getRankDate() {
        return rankDate;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }

    public void setMovieSeq(Long movieNo) {
        this.movieSeq = movieSeq;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String seq) {
        this.rank = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Builder
    public Movie(String rank, String title, String rankDate){
        this.rank = rank;
        this.title = title;
        this.rankDate = rankDate;
    }


}
