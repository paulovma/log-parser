package com.quake.arena.logparser.domain.linereader;

import com.quake.arena.logparser.domain.gamemanager.GameManager;

class GameInitLineReader implements LineReader {

    private static final String KEY = "InitGame";

    @Override
    public void read(String line, GameManager bag) {
        if (line.contains(KEY)) {
            bag.newGame();
        }
    }
}
