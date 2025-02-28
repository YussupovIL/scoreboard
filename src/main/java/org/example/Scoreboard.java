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
       Optional<Match> foundMatch =  findMatch(homeTeam, awayTeam);
       if(!foundMatch.isPresent()){
           throw new IllegalArgumentException("no match with such teams");
       }
       matches.remove(foundMatch.get());

    }

    public void updateScore(String teamHome, String awayTeam, int homeScore, int awayScore){
        Optional<Match> foundMatch =  findMatch(teamHome, awayTeam);
        if(!foundMatch.isPresent()){
            throw new IllegalArgumentException("no match with such teams");
        }
        foundMatch.get().setHomeScore(homeScore);
        foundMatch.get().setAwayScore(awayScore);
    }

    public List<Match> getSummary(){

        return matches;
    }

    /**
     * helper method to filter the list
     * @param teamHome
     * @param teamAway
     * @return match
     */
    private Optional<Match> findMatch(String teamHome, String teamAway){
        return matches.stream().filter(m -> teamHome.equals(m.getHomeTeam()) && teamAway.equals(m.getAwayTeam())).findFirst();
    }
}
