package com.quake.arena.logparser.domain.linereader;

import com.quake.arena.logparser.domain.gamemanager.GameManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PlayerLineReader implements LineReader {

    private final String key = "ClientUserinfoChanged";

    @Override
    public void read(String line, GameManager bag) {
        if (line.contains(key)) {
            Pattern pattern = Pattern.compile("(?<=n\\\\)(.*?)(?=\\\\t)");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                bag.addPlayer(matcher.group(1));
            }
        }
    }
}
