package com.quake.arena.logparser.domain.logretriever;

import com.quake.arena.logparser.domain.model.GameLogFilter;
import com.quake.arena.logparser.domain.model.Log;
import reactor.core.publisher.Flux;

public interface LogRetriever {

    Flux<Log> retrieveAll(GameLogFilter gameLogFilter);
}
