package com.quake.arena.logparser.domain.linereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameInitLineReaderTest {

    private static final String INIT_GAME_LINE =
        """
            0:00 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner
        """;

    private static final String KILL_LINE =
        """
             20:54 Kill: 1022 2 22: <world> killed Isgalamido by MOD_TRIGGER_HURT
        """;

    private final FakeGameManager fakeGameManager = new FakeGameManager();;
    private GameInitLineReader gameInitLineReader;

    @BeforeEach
    public void setUp() {
        gameInitLineReader = new GameInitLineReader();
    }

    @Test
    public void testReadShouldIncrementGameCountBecauseGivenLineIsAboutInitGame() {
        gameInitLineReader.read(INIT_GAME_LINE, fakeGameManager);
        Assertions.assertEquals(1, fakeGameManager.getGameCount());
    }

    @Test
    public void testReadShouldNotIncrementGameCountBecauseGivenLineIsNotAboutInitGame() {
        gameInitLineReader.read(KILL_LINE, fakeGameManager);
        Assertions.assertEquals(0, fakeGameManager.getGameCount());
    }
}
