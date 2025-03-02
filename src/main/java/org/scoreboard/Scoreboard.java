package org.scoreboard;

import java.util.*;

public class Scoreboard {

    private List<Match> matches = new ArrayList<>();
    private Set<String> participatingTeams = new HashSet<>();
    private int startOrderCounter = 0;

    public void startMatch(String homeTeam, String awayTeam) {
        if (homeTeam == null || homeTeam.isEmpty() || awayTeam == null || awayTeam.isEmpty())
            throw new IllegalArgumentException("Team names cannot be null or empty");

        if (participatingTeams.contains(homeTeam) || participatingTeams.contains(awayTeam))
            throw new IllegalArgumentException("At least one of the teams is already playing");

        participatingTeams.add(homeTeam);
        participatingTeams.add(awayTeam);

        Match match = new Match(homeTeam, awayTeam, startOrderCounter);
        matches.add(match);

        startOrderCounter++;
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Optional<Match> foundMatch = findMatch(homeTeam, awayTeam);
        if (foundMatch.isEmpty())
            throw new IllegalArgumentException("No match with such teams");

        matches.remove(foundMatch.get());
        participatingTeams.remove(homeTeam);
        participatingTeams.remove(awayTeam);

    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Optional<Match> foundMatch = findMatch(homeTeam, awayTeam);
        if (foundMatch.isEmpty())
            throw new IllegalArgumentException("No match with such teams");

        foundMatch.get().updateScore(homeScore, awayScore);
    }



    /**
     * Return a summary of all ongoing matches sorted by total score in descending order, then by start order in descending order.
     * @return a list of strings representing the summary of all ongoing matches
     */
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
     * Helper method to filter the list
     * @param homeTeam
     * @param awayTeam
     * @return match
     */
    private Optional<Match> findMatch(String homeTeam, String awayTeam) {
        if (homeTeam == null || homeTeam.isEmpty() || awayTeam == null || awayTeam.isEmpty())
            throw new IllegalArgumentException("Team names cannot be null or empty");


        return matches.stream().filter(m -> homeTeam.equals(m.getHomeTeam()) && awayTeam.equals(m.getAwayTeam())).findFirst();
    }


    /**
     * Helper method for tests
     * Get the list of matches
     * @return a list of matches in progress
     */
    public List<Match> getMatches() {
        return matches;
    }

}
