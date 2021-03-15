package com.quake.arena.logparser.infrastructure.repository;

import com.quake.arena.logparser.domain.model.GameLogFilter;
import com.quake.arena.logparser.domain.model.Log;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public sealed interface ReactiveRepository permits LogRepositoryImpl {

    Flux<Log> findAll(GameLogFilter gameLogFilter);

    Mono<Log> save(Log log);

    Mono<Log> update(Log log);

}
