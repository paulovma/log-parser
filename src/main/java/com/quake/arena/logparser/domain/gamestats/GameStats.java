package com.quake.arena.logparser.domain.gamestats;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ToString
@NoArgsConstructor
@Data
public class GameStats {

    private String gameName;

    private Map<String, Integer> kills;

    private Set<String> players;

    private Integer totalKills;

    public GameStats(String gameName) {
        this.gameName = gameName;
        kills = new HashMap<>();
        players = new HashSet<>();
        totalKills = 0;
    }
}
