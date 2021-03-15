package com.quake.arena.logparser.domain.linereader;

import com.quake.arena.logparser.domain.gamemanager.GameManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WorldKillLineReader implements LineReader{

    private static final String KEY = "Kill";
    private static final String WORLD_KEY = "world";

    @Override
    public void read(String line, GameManager bag) {
        if (line.contains(KEY) && line.contains(WORLD_KEY)) {
            Pattern pattern = Pattern.compile("(?<=killed )(.*?)(?=\\s*by)");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                bag.decrementPlayerKill(matcher.group(1));
            }
        }
    }
}
