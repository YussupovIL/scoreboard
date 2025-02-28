package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Scoreboard {

    List<Match> matches = new ArrayList<>();

    public void startMatch(String homeTeam,  String awayTeam){
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
    }

    public void finishMatch(String homeTeam, String awayTeam){


    }

    public void updateScore(String teamHome, String awayTeam, int homeScore, int awayScore){

    }

    public List<Match> getSummary(){

        return matches;
    }

}
