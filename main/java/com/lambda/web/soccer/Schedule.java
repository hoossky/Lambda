package com.lambda.web.soccer;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="schedule")

public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheNo;
    @NotNull @Column(length = 10) private String gubun;
    @Column(length = 10) private String hometeamId;
    @Column(length = 10) private String awayteamId;
    @Column(length = 10) private String homeScore;
    @Column(length = 10) private String awayScore;

    public Long getScheNo() {
        return scheNo;
    }

    public void setScheNo(Long scheNo) {
        this.scheNo = scheNo;
    }

    public String getGubun() {
        return gubun;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
    }

    public String getHometeamId() {
        return hometeamId;
    }

    public void setHometeamId(String hometeamId) {
        this.hometeamId = hometeamId;
    }

    public String getAwayteamId() {
        return awayteamId;
    }

    public void setAwayteamId(String awayteamId) {
        this.awayteamId = awayteamId;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public Stadium getStadiumOfSchedule() {
        return stadiumOfSchedule;
    }

    public void setStadiumOfSchedule(Stadium stadiumOfSchedule) {
        this.stadiumOfSchedule = stadiumOfSchedule;
    }

    @Builder
    public Schedule(String gubun, String hometeamId, String awayteamId,
                    String homeScore, String awayScore){
        this.gubun=gubun;
        this.hometeamId=hometeamId;
        this.awayteamId=awayteamId;
        this.homeScore=homeScore;
        this.awayScore=awayScore;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id") @NotNull
    private Stadium stadiumOfSchedule;
}