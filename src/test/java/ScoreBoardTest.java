import org.example.Match;
import org.example.Scoreboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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






}
