package com.quake.arena.logparser.application;

import com.quake.arena.logparser.domain.model.Log;
import com.quake.arena.logparser.domain.logstorer.LogStorer;
import com.quake.arena.logparser.infrastructure.repository.ReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
public class DatabaseGameStorer implements LogStorer {

    @Autowired
    private ReactiveRepository reactiveRepository;

    @Transactional
    @Override
    public Mono<Log> store(Log log) {
        return reactiveRepository.save(log);
    }

    @Override
    public Mono<Log> update(Log log) {
        return reactiveRepository.update(log);
    }
}
