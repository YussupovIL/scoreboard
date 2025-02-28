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
        scoreboard.startMatch("home", "away");

        //then
        assertEquals(scoreboard.getSummary().size(), 1);
    }





}
