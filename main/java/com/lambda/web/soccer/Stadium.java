package com.lambda.web.soccer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity (name ="stadium")

public class Stadium {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stadiumNo;
    @NotNull @Column(length = 10)
    private String stadiumId,stadiumName,hometeamId,seatCount,ddd,tel;
    @Column(length = 80)
    private String address;

    public Long getStadiumNo() {
        return stadiumNo;
    }

    public void setStadiumNo(Long stadiumNo) {
        this.stadiumNo = stadiumNo;
    }

    public String getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(String stadiumId) {
        this.stadiumId = stadiumId;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getHometeamId() {
        return hometeamId;
    }

    public void setHometeamId(String hometeamId) {
        this.hometeamId = hometeamId;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Builder
    public Stadium(String stadiumId,
                   String stadiumName,
                   String hometeamId,
                   String seatCount,
                   String ddd,
                   String tel,
                   String address){

        this.stadiumId = stadiumId;
        this.stadiumName = stadiumName;
        this.hometeamId = hometeamId;
        this.seatCount = seatCount;
        this.ddd = ddd;
        this.tel = tel;
        this.address = address;

    }

    @OneToMany(mappedBy = "stadiumOfTeam")
    private List<Team> teams;
    @OneToMany(mappedBy = "stadiumOfSchedule")
    private List<Schedule> schedules;
}