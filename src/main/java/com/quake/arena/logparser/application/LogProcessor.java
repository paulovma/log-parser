package com.quake.arena.logparser.application;

import com.quake.arena.logparser.domain.model.Game;
import com.quake.arena.logparser.domain.model.Log;
import com.quake.arena.logparser.domain.model.LogStatus;
import com.quake.arena.logparser.domain.gamemanager.GameManager;
import com.quake.arena.logparser.domain.gamemanager.QuakeManager;
import com.quake.arena.logparser.domain.gamestats.GameStats;
import com.quake.arena.logparser.domain.linereader.LineReader;
import com.quake.arena.logparser.domain.linereader.LineReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LogProcessor implements com.quake.arena.logparser.domain.logprocessor.LogProcessor {

    private GameManager manager;

    public Log process(BufferedReader bufferedReader, UUID logIdentifier) {
        log.info("M=process, I=Starting proccess for : {}", logIdentifier);
        Set<Game> games = new HashSet<>();
        Set<LineReader> readers = (new LineReaderBuilder())
                .withPlayerKillReader()
                .withWorldKillReader()
                .withPlayerReader()
                .get();

        manager = new QuakeManager();
        bufferedReader
                .lines()
                .forEach(l -> {
                    readers.forEach(r -> r.read(l, manager));
                });

        Map<String, GameStats> gameStatsMap = manager.gameStatsMap();

        if (gameStatsMap.isEmpty()) {
            throw new IllegalStateException();
        }

        gameStatsMap.forEach((k, v) -> {
            Game game = new Game();
            game.fromGameStats(v);
            games.add(game);
        });

        log.info("M=process, I=Proccess finished for : {}", logIdentifier);

        return new Log(UUID.randomUUID().toString(), logIdentifier, games, LogStatus.PROCESSED);
    }

}
