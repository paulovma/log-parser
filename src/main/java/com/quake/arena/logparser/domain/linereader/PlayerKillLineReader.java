package com.quake.arena.logparser.domain.linereader;

import com.quake.arena.logparser.domain.gamemanager.GameManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PlayerKillLineReader implements LineReader {

    private final String key = "Kill";

    @Override
    public void read(String line, GameManager bag) {
        if (line.contains("Kill") && !line.contains("world")) {
            Pattern pattern = Pattern.compile("(?<=(\\d): )(.*?)(?=\\s*killed)");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                bag.incrementPlayerKill(matcher.group(2));//TODO verificar por que 2 grupos dao match
            }
        }
    }
}
