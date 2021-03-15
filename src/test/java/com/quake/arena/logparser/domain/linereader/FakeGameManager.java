package com.quake.arena.logparser.domain.linereader;

import com.quake.arena.logparser.domain.gamemanager.GameManager;
import com.quake.arena.logparser.domain.gamestats.GameStats;

import java.util.Map;

public class FakeGameManager implements GameManager {

    private int gameCount = 0;
    private int playerCount = 0;
    private int playerKillCount = 0;

    @Override
    public void addPlayer(String playerName) {
        playerCount++;
    }

    @Override
    public int newGame() {
        return gameCount++;
    }

    @Override
    public void decrementPlayerKill(String player) {
        playerKillCount--;
    }

    @Override
    public void incrementPlayerKill(String player) {
        playerKillCount++;
    }

    @Override
    public Map<String, GameStats> gameStatsMap() {
        return null;
    }

    public int getGameCount() {
        return gameCount;
    }
    public int getPlayerKillCount() { return playerKillCount; }
    public int getPlayerCount() { return playerCount; }

}
