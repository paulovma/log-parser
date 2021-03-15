package com.quake.arena.logparser.domain.model;

import com.quake.arena.logparser.domain.gamestats.GameStats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Game {

    private String gameName;

    private Map<String, Integer> kills;

    private Set<String> players;

    private Integer totalKills;

    public void fromGameStats(GameStats gameStats) {
        gameName = gameStats.getGameName();
        kills = gameStats.getKills();
        players = gameStats.getPlayers();
        totalKills = gameStats.getTotalKills();
    }
}
