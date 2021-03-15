package com.quake.arena.logparser.domain.linereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerLineReaderTest {

    private static final String WORLD_KILL =
            """
                 21:07 Kill: 1022 2 22: <world> killed Isgalamido by MOD_TRIGGER_HURT
            """;

    private static final String PLAYER_CONNECTED =
            """
                   20:34 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0
            """;

    private final FakeGameManager fakeGameManager = new FakeGameManager();;

    private PlayerLineReader playerLineReader;

    @BeforeEach
    public void setUp() {
        playerLineReader = new PlayerLineReader();
    }

    @Test
    public void testReadShouldIncrementPlayerCountBecauseThisIsALineAboutPlayerKilling() {
        playerLineReader.read(PLAYER_CONNECTED, fakeGameManager);
        Assertions.assertEquals(1, fakeGameManager.getPlayerCount());
    }

    @Test
    public void testReadShouldNotIncrementPlayerCountBecauseThisIsNotALineAboutPlayerKilling() {
        playerLineReader.read(WORLD_KILL, fakeGameManager);
        Assertions.assertEquals(0, fakeGameManager.getPlayerCount());
    }
}
