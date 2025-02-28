import org.example.Match;
import org.example.Scoreboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreBoardTest {

    @Test
    public void startMatchTest(){
        //given
        Scoreboard scoreboard = new Scoreboard();

        //when
        scoreboard.startMatch("Nigeria", "Germany");

        //then
        assertEquals(scoreboard.getSummary().size(), 1);
        assertEquals(0, scoreboard.getSummary().get(0).getHomeScore());
        assertEquals( 0, scoreboard.getSummary().get(0).getAwayScore());
        assertEquals("Nigeria", scoreboard.getSummary().get(0).getHomeTeam());
        assertEquals("Germany", scoreboard.getSummary().get(0).getAwayTeam());

    }

    @Test
    public void removeMatchTest(){
        //given
        Scoreboard scoreboard = new Scoreboard();

        //when
        scoreboard.startMatch("Nigeria", "Germany");
        scoreboard.finishMatch("Nigeria", "Germany");

        //then
        assertEquals(0, scoreboard.getSummary().size());
    }

    @Test
    public void updateScoreTest(){
        //given
        Scoreboard scoreboard = new Scoreboard();

        //when
        scoreboard.startMatch("Nigeria", "Germany");
        scoreboard.updateScore("Nigeria", "Germany", 2, 2);

        //then
        assertEquals(2, scoreboard.getSummary().get(0).getHomeScore());
        assertEquals(2, scoreboard.getSummary().get(0).getAwayScore());
    }

    @Test
    public void getSummaryTest(){
        //given
        Scoreboard scoreboard = new Scoreboard();
        Scoreboard checkScoreboard = new Scoreboard();

        //when
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.startMatch("Argentina", "Australia");

        scoreboard.updateScore("Mexico", "Canada",0,5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia",3, 1);

        checkScoreboard.startMatch("Uruguay", "Italy");
        checkScoreboard.startMatch("Spain", "Brazil");
        checkScoreboard.startMatch("Mexico", "Canada");
        checkScoreboard.startMatch("Argentina", "Australia");
        checkScoreboard.startMatch("Germany", "France");

        scoreboard.updateScore("Mexico", "Canada",0,5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia",3, 1);


        //then
       assertTrue(scoreboard.getSummary().equals(checkScoreboard.getSummary()));


    }






}
