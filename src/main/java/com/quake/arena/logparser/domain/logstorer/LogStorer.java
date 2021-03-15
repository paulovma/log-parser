package com.quake.arena.logparser.domain.logstorer;


import com.quake.arena.logparser.domain.model.Log;
import reactor.core.publisher.Mono;

public interface LogStorer {

    Mono<Log> store(Log log);

    Mono<Log> update(Log log);
}
