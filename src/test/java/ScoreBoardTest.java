import org.scoreboard.Scoreboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {


    @Test
    public void startMatchInitialScoreTest() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Nigeria", "Germany");

        assertEquals( 1, scoreboard.getMatches().size());
        assertEquals(0, scoreboard.getMatches().get(0).getHomeScore());
        assertEquals(0, scoreboard.getMatches().get(0).getAwayScore());
    }

    @Test
    public void startMatchWithNullTeams(){
        Scoreboard scoreboard = new Scoreboard();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch(null, null);
        });
        assertTrue(exception.getMessage().contains("Team names cannot be null or empty"));
    }

    @Test
    public void startMatchTeamsAssignmentTest(){
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Nigeria", "Germany");

        assertEquals("Nigeria", scoreboard.getMatches().get(0).getHomeTeam());
        assertEquals("Germany", scoreboard.getMatches().get(0).getAwayTeam());
    }

    @Test
    public void startMatchWithOccupiedTeamsTest() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Nigeria", "Germany");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch("Nigeria", "USA");
        });
        assertTrue(exception.getMessage().contains("At least one of the teams is already playing"));
    }

    @Test
    public void startMatchWithSameTeamsTest() {
        Scoreboard scoreboard = new Scoreboard();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch("Nigeria", "Nigeria");
        });
        assertTrue(exception.getMessage().contains("Teams should be different"));
    }


    @Test
    public void finishMatchAmountOfMatchesTest() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Nigeria", "Germany");
        scoreboard.finishMatch("Nigeria", "Germany");

        assertEquals(0, scoreboard.getMatches().size());
    }

    @Test
    public void finishMatchWithCurrentlyNotPlayingTeamsTest(){
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Nigeria", "Germany");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.finishMatch("Mexico", "USA");
        });
        assertTrue(exception.getMessage().contains("No match with such teams"));
    }


    @Test
    public void updateScoreWithValidScoresTest() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Nigeria", "Germany");
        scoreboard.updateScore("Nigeria", "Germany", 2, 2);

        assertEquals(2, scoreboard.getMatches().get(0).getHomeScore());
        assertEquals(2, scoreboard.getMatches().get(0).getAwayScore());
    }

    @Test
    public void updateScoreWithNegativeScoresTest(){
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Nigeria", "Germany");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.updateScore("Nigeria", "Germany", -2, -3);
        });
        assertTrue(exception.getMessage().contains("Scores cannot be negative"));
    }

    @Test
    public void updateScoreOnNonExistingMatch(){
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Nigeria", "Germany");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           scoreboard.updateScore("Mexico", "USA", 2, 2);
        });
        assertTrue(exception.getMessage().contains("No match with such teams"));
    }

    @Test
    public void getSummarySortingTest() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.startMatch("Argentina", "Australia");

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        String[] expected = {
                "Uruguay 6 - Italy 6",
                "Spain 10 - Brazil 2",
                "Mexico 0 - Canada 5",
                "Argentina 3 - Australia 1",
                "Germany 2 - France 2"
        };

        assertArrayEquals(expected, scoreboard.getSummary().toArray());
    }

    @Test
    public void getSummaryWhenNoMatches() {
        Scoreboard scoreboard = new Scoreboard();

        assertTrue(scoreboard.getSummary().isEmpty());
    }


}
