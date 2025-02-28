package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Scoreboard {

    List<Match> matches = new ArrayList<>();
    private int startOrderCounter = 0;

    public void startMatch(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam, startOrderCounter);
        matches.add(match);
        startOrderCounter++;
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Optional<Match> foundMatch = findMatch(homeTeam, awayTeam);
        if (!foundMatch.isPresent()) {
            throw new IllegalArgumentException("no match with such teams");
        }
        matches.remove(foundMatch.get());

    }

    public void updateScore(String teamHome, String awayTeam, int homeScore, int awayScore) {
        Optional<Match> foundMatch = findMatch(teamHome, awayTeam);
        if (!foundMatch.isPresent()) {
            throw new IllegalArgumentException("No match with such teams");
        }
        foundMatch.get().updateScore(homeScore, awayScore);
    }

    public List<String> getSummary() {
        List<Match> sortedMatches = new ArrayList<>(matches);
        sortedMatches.sort(
                Comparator.comparing(Match::getTotalScore).reversed()
                        .thenComparing(Comparator.comparing(Match::getStartOrder).reversed())
        );
        List<String> summary = new ArrayList<>();
        sortedMatches.forEach((match -> summary.add(match.getSummary())));
        return summary;
    }

    /**
     * helper method to filter the list
     *
     * @param teamHome
     * @param teamAway
     * @return match
     */
    private Optional<Match> findMatch(String teamHome, String teamAway) {
        return matches.stream().filter(m -> teamHome.equals(m.getHomeTeam()) && teamAway.equals(m.getAwayTeam())).findFirst();
    }

    // For testing purposes:
    public List<Match> getMatches() {
        return matches;

    }

}
