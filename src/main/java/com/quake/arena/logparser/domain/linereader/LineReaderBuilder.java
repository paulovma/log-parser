package com.quake.arena.logparser.domain.linereader;

import java.util.HashSet;
import java.util.Set;

public class LineReaderBuilder {

    private Set<LineReader> readers;

    public LineReaderBuilder() {
        readers = new HashSet<>();
        readers.add(new GameInitLineReader());
    }

    public LineReaderBuilder withPlayerKillReader() {
        readers.add(new PlayerKillLineReader());
        return this;
    }

    public LineReaderBuilder withPlayerReader() {
        readers.add(new PlayerLineReader());
        return this;
    }

    public LineReaderBuilder withWorldKillReader() {
        readers.add(new WorldKillLineReader());
        return this;
    }

    public Set<LineReader> get() {
        return Set.copyOf(readers);
    }

}
