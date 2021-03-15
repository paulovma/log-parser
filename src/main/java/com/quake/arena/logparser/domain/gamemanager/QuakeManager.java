package com.quake.arena.logparser.domain.gamemanager;

import com.quake.arena.logparser.domain.gamestats.GameStats;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

//TODO IMPLEMENTAR 4. Caso o player mate a si próprio, não deverá ser contabilizado no dicionários de kills
public class QuakeManager implements GameManager {

    private final AtomicInteger gameCount = new AtomicInteger(0);
    private final String gameKey = "Game_";
    private final Map<String, Set<String>> gameKills = new HashMap<>();

    private final Map<String, GameStats> gameStatsMap = new HashMap<>();

    public void addPlayer(String playerName) {
        gameInitializedValidation();
        GameStats gameStats = gameStatsMap.computeIfAbsent(getGameKey(), GameStats::new);
        gameStats.getPlayers().add(playerName);

    }

    private void gameInitializedValidation() {
        if (gameCount.get() == 0) {
            throw new IllegalStateException("A Game has not been initialized yet.");
        }
    }

    public int newGame() {
        return gameCount.incrementAndGet();
    }

    public void decrementPlayerKill(String player) {
        gameInitializedValidation();
        GameStats gameStats = gameStatsMap.computeIfAbsent(getGameKey(), GameStats::new);
        gameStats.getKills().compute(player, (k, v) -> v == null ? - 1 : v - 1);
        gameStats.setTotalKills(gameStats.getTotalKills() + 1);
    }

    public void incrementPlayerKill(String player) {
        gameInitializedValidation();
        GameStats gameStats = gameStatsMap.computeIfAbsent(getGameKey(), GameStats::new);
        gameStats.getKills().compute(player, (k, v) -> v == null ? 1 : v + 1);
        gameStats.setTotalKills(gameStats.getTotalKills() + 1);
    }

    public Map<String, GameStats> gameStatsMap() {
        return Map.copyOf(gameStatsMap);
    }

    private String getGameKey() {
        gameInitializedValidation();
        return gameKey.concat(String.valueOf(gameCount));
    }

}
