package org.example;

import java.util.Objects;

public class Match {

    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;

    public int getStartOrder() {
        return startOrder;
    }

    private final int startOrder;

    public Match(String homeTeam, String awayTeam, int startOrder) {
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
        this.startOrder = startOrder;
        homeScore = 0;
        awayScore = 0;
    }

    public void updateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getTotalScore(){
        return awayScore + homeScore;
    }

    public String getSummary() {
        return String.format("%s %d - %s %d", homeTeam, homeScore, awayTeam, awayScore);
    }


}
