package com.quake.arena.logparser.domain.gamemanager;

import com.quake.arena.logparser.domain.gamestats.GameStats;

import java.util.Map;

public interface GameManager {

    void addPlayer(String playerName);
    int newGame();
    void decrementPlayerKill(String player);
    void incrementPlayerKill(String player);
    Map<String, GameStats> gameStatsMap();
}
