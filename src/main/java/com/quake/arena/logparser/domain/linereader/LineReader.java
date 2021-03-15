package com.quake.arena.logparser.domain.linereader;

import com.quake.arena.logparser.domain.gamemanager.GameManager;

public interface LineReader {

    void read(String line, GameManager bag);
}
