package com.quake.arena.logparser.domain.linereader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LineReaderBuilderTest {

    private LineReaderBuilder lineReaderBuilder;

    @BeforeEach
    public void setUp() {
        lineReaderBuilder = new LineReaderBuilder();
    }

    @Test
    public void shouldContainOnlyGameInitLineReaderInReaderList() {
        Assertions.assertAll(
            () -> Assertions.assertEquals(1, lineReaderBuilder.get().size()),
            () -> Assertions.assertEquals(0, lineReaderBuilder.get().stream().filter(t -> !(t instanceof GameInitLineReader)).count())
        );
    }

    @Test
    public void shouldContainPlayerLineReaderInReaderList() {
        lineReaderBuilder.withPlayerReader();
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, lineReaderBuilder.get().size()),
                () -> Assertions.assertEquals(1, lineReaderBuilder.get().stream().filter(t -> !(t instanceof PlayerLineReader)).count())
        );
    }

    @Test
    public void shouldContainPlayerKillLineReaderInReaderList() {
        lineReaderBuilder.withPlayerKillReader();
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, lineReaderBuilder.get().size()),
                () -> Assertions.assertEquals(1, lineReaderBuilder.get().stream().filter(t -> !(t instanceof PlayerKillLineReader)).count())
        );
    }

    @Test
    public void shouldContainWorldKillLineReaderInReaderList() {
        lineReaderBuilder.withWorldKillReader();
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, lineReaderBuilder.get().size()),
                () -> Assertions.assertEquals(1, lineReaderBuilder.get().stream().filter(t -> !(t instanceof WorldKillLineReader)).count())
        );
    }
}
