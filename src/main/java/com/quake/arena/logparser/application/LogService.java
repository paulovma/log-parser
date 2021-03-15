package com.quake.arena.logparser.application;

import com.quake.arena.logparser.domain.model.GameLogFilter;
import com.quake.arena.logparser.domain.model.Log;
import com.quake.arena.logparser.domain.model.LogStatus;
import com.quake.arena.logparser.domain.logretriever.LogRetriever;
import com.quake.arena.logparser.domain.logprocessor.LogProcessor;
import com.quake.arena.logparser.domain.logstorer.LogStorer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Service
public class LogService {

    @Autowired
    private LogProcessor logProcessor;

    @Autowired
    private LogStorer logStorer;

    @Autowired
    private LogRetriever gameRetriever;

    @Async
    public void read(MultipartFile file, UUID logIdentifier) throws InterruptedException {
        log.info("M=read, I=identifier: {}", logIdentifier);
        Log logModel = Log.builder().identifier(logIdentifier).status(LogStatus.PROCESSING).build();

        try (InputStream inputStream = file.getInputStream()) {
            logModel = logStorer.store(logModel).block();
            log.info("M=read, I=log saved with status: {}, ID: {}", logModel.getStatus(), logModel.getId());

            Log processedLog = logProcessor
                    .process(
                            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)),
                            logIdentifier
                    );

            logModel.setGames(processedLog.getGames());
            logModel.setStatus(processedLog.getStatus());
            processedLog.setId(logModel.getId());
        } catch (IOException e) {
            logModel.setStatus(LogStatus.NOT_PROCESSED);
        }

        logStorer.update(logModel).subscribe();
    }

    public Flux<Log> findAll(UUID identifier, String gameName) {
        return gameRetriever.retrieveAll(new GameLogFilter(identifier, gameName));
    }
}
