import org.example.Match;
import org.example.Scoreboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {

    @Test
    public void startMatchTest(){
        //given
        Scoreboard scoreboard = new Scoreboard();

        //when
        scoreboard.startMatch("Nigeria", "Germany");

        //then
        assertEquals(scoreboard.getSummary().size(), 1);
        assertEquals(0, scoreboard.getMatches().get(0).getHomeScore());
        assertEquals( 0, scoreboard.getMatches().get(0).getAwayScore());
        assertEquals("Nigeria", scoreboard.getMatches().get(0).getHomeTeam());
        assertEquals("Germany", scoreboard.getMatches().get(0).getAwayTeam());

    }

    @Test
    public void removeMatchTest(){
        //given
        Scoreboard scoreboard = new Scoreboard();

        //when
        scoreboard.startMatch("Nigeria", "Germany");
        scoreboard.finishMatch("Nigeria", "Germany");

        //then
        assertEquals(0, scoreboard.getMatches().size());
    }

    @Test
    public void updateScoreTest(){
        //given
        Scoreboard scoreboard = new Scoreboard();

        //when
        scoreboard.startMatch("Nigeria", "Germany");
        scoreboard.updateScore("Nigeria", "Germany", 2, 2);

        //then
        assertEquals(2, scoreboard.getMatches().get(0).getHomeScore());
        assertEquals(2, scoreboard.getMatches().get(0).getAwayScore());
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

        String[] expected = {
                "Uruguay 6 - Italy 6",
                "Spain 10 - Brazil 2",
                "Mexico 0 - Canada 5",
                "Argentina 3 - Australia 1",
                "Germany 2 - France 2"
        };

        //then
       assertArrayEquals(expected, scoreboard.getSummary().toArray());
    }






}
