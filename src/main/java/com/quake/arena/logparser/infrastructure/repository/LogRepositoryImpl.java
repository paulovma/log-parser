package com.quake.arena.logparser.infrastructure.repository;

import com.quake.arena.logparser.domain.model.GameLogFilter;
import com.quake.arena.logparser.domain.model.LogStatus;
import com.quake.arena.logparser.infrastructure.repository.entities.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
non-sealed class LogRepositoryImpl implements ReactiveRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<com.quake.arena.logparser.domain.model.Log> findAll(GameLogFilter gameLogFilter) {
        Query query = new Query();
        if (!ObjectUtils.isEmpty(gameLogFilter.gameName())) {
            query.addCriteria(Criteria.where("games.gameName").is(gameLogFilter.gameName()));
            query.fields().include("games.$").include("identifier");
        }

        if (!ObjectUtils.isEmpty(gameLogFilter.identifier())) {
            query.addCriteria(Criteria.where("identifier").is(gameLogFilter.identifier()));
        }

        return reactiveMongoTemplate.find(query, Log.class).map(this::toDomain);
    }

    @Override
    public Mono<com.quake.arena.logparser.domain.model.Log> save(com.quake.arena.logparser.domain.model.Log log) {
        return reactiveMongoTemplate.save(
                Log
                    .builder()
                    .games(log.getGames())
                    .identifier(log.getIdentifier())
                    .status(log.getStatus())
                    .build()
        ).map(this::toDomain);
    }

    @Override
    public Mono<com.quake.arena.logparser.domain.model.Log> update(com.quake.arena.logparser.domain.model.Log log) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(log.getId()));
        Log persistedLog = reactiveMongoTemplate.findOne(query, Log.class).block();
        persistedLog.setGames(log.getGames());
        persistedLog.setStatus(log.getStatus());

        return reactiveMongoTemplate.save(persistedLog).map(this::toDomain);
    }

    private com.quake.arena.logparser.domain.model.Log toDomain(Log log) {
        return
                com.quake.arena.logparser.domain.model.Log
                    .builder()
                    .identifier(log.getIdentifier())
                    .games(log.getGames())
                    .id(log.getId())
                    .status(log.getStatus())
                    .build();
    }
}
