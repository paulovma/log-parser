package com.quake.arena.logparser.application;

import com.quake.arena.logparser.domain.model.GameLogFilter;
import com.quake.arena.logparser.domain.model.Log;
import com.quake.arena.logparser.domain.logretriever.LogRetriever;
import com.quake.arena.logparser.infrastructure.repository.ReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DatabaseGameRetriever implements LogRetriever {

    @Autowired
    private ReactiveRepository reactiveRepository;

    @Override
    public Flux<Log> retrieveAll(GameLogFilter gameLogFilter) {
        return reactiveRepository.findAll(gameLogFilter);
    }
}
