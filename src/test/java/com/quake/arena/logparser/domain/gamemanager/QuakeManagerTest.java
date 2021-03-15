package com.quake.arena.logparser.domain.gamemanager;

import com.quake.arena.logparser.domain.gamestats.GameStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class QuakeManagerTest {

    private static QuakeManager quakeManager;

    @BeforeEach
    public void setUp() {
        quakeManager = new QuakeManager();
    }

    @Test
    public void testNewGameShouldReturnOneBecauseThisIsTheFirstGameCreated() {
        int gameIndex = quakeManager.newGame();
        Assertions.assertEquals(1, gameIndex);
    }

    @Test
    public void testNewGameShouldIncrementGameCountByOne() {
        quakeManager.newGame();
        quakeManager.newGame();
        quakeManager.newGame();
        quakeManager.newGame();
        int gameIndex = quakeManager.newGame();


        Assertions.assertEquals(5, gameIndex);
    }

    @Test
    public void testAddPlayerPlayerNameShouldBeInGameStatsPlayersList() {
        quakeManager.newGame();
        String playerName = "Player_1";
        quakeManager.addPlayer(playerName);
        Map<String, GameStats> gameStatsMap = quakeManager.gameStatsMap();
        GameStats gameStats = gameStatsMap.get("Game_1");

        Assertions.assertTrue(gameStats.getPlayers().contains(playerName));
    }

    @Test
    public void testAddPlayerShouldThrowIllegalStateExceptionBecauseAGameHasNotBeenInitialized() {
        Assertions.assertThrows(IllegalStateException.class, () -> quakeManager.addPlayer("Player_1"));
    }

    @Test
    public void testIncrementPlayerKillExpectingCorrectCounting() {
        quakeManager.newGame();
        String playerName = "Player_1";
        quakeManager.incrementPlayerKill(playerName);
        quakeManager.incrementPlayerKill(playerName);
        quakeManager.incrementPlayerKill(playerName);

        Map<String, GameStats> gameStatsMap = quakeManager.gameStatsMap();
        GameStats gameStats = gameStatsMap.get("Game_1");

        Map<String, Integer> playersKills = gameStats.getKills();

        Assertions.assertEquals(3, playersKills.get(playerName));
    }

    @Test
    public void testIncrementPlayerKillExpectingIllegalStateExceptionBecauseAGameHasNotBeenInitialized() {
        Assertions.assertThrows(IllegalStateException.class, () -> quakeManager.incrementPlayerKill("Player_1"));
    }


    @Test
    public void testDecrementPlayerKillExpectingCorrectCounting() {
        quakeManager.newGame();
        String playerName = "Player_1";
        quakeManager.decrementPlayerKill(playerName);
        quakeManager.decrementPlayerKill(playerName);
        quakeManager.decrementPlayerKill(playerName);

        Map<String, GameStats> gameStatsMap = quakeManager.gameStatsMap();
        GameStats gameStats = gameStatsMap.get("Game_1");

        Map<String, Integer> playersKills = gameStats.getKills();

        Assertions.assertEquals(-3, playersKills.get(playerName));
    }

    @Test
    public void testDecrementPlayerKillExpectingIllegalStateExceptionBecauseAGameHasNotBeenInitialized() {
        Assertions.assertThrows(IllegalStateException.class, () -> quakeManager.decrementPlayerKill("Player_1"));
    }

    @Test
    public void testDecrementAndIncrementPlayerKillExpectingCorrectCounting() {
        quakeManager.newGame();
        String playerName = "Player_1";
        quakeManager.decrementPlayerKill(playerName);
        quakeManager.decrementPlayerKill(playerName);
        quakeManager.decrementPlayerKill(playerName);

        quakeManager.incrementPlayerKill(playerName);
        quakeManager.incrementPlayerKill(playerName);
        quakeManager.incrementPlayerKill(playerName);
        quakeManager.incrementPlayerKill(playerName);


        Map<String, GameStats> gameStatsMap = quakeManager.gameStatsMap();
        GameStats gameStats = gameStatsMap.get("Game_1");

        Map<String, Integer> playersKills = gameStats.getKills();

        Assertions.assertEquals(1, playersKills.get(playerName));
    }

}
