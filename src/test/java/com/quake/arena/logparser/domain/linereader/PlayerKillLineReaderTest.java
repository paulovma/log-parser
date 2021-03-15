package com.quake.arena.logparser.domain.linereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerKillLineReaderTest {

    private static final String WORLD_KILL =
        """
             21:07 Kill: 1022 2 22: <world> killed Isgalamido by MOD_TRIGGER_HURT
        """;

    private static final String PLAYER_KILL =
        """
              22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH
        """;

    private final FakeGameManager fakeGameManager = new FakeGameManager();;

    private PlayerKillLineReader playerKillLineReader;

    @BeforeEach
    public void setUp() {
        playerKillLineReader = new PlayerKillLineReader();
    }

    @Test
    public void testReadShouldIncrementPlayerKillCountBecauseThisIsALineAboutPlayerKilling() {
        playerKillLineReader.read(PLAYER_KILL, fakeGameManager);
        Assertions.assertEquals(1, fakeGameManager.getPlayerKillCount());
    }

    @Test
    public void testReadShouldNotIncrementPlayerKillCountBecauseThisIsNotALineAboutPlayerKilling() {
        playerKillLineReader.read(WORLD_KILL, fakeGameManager);
        Assertions.assertEquals(0, fakeGameManager.getPlayerKillCount());
    }
}
