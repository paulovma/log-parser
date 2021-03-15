package com.quake.arena.logparser.infrastructure.http;

import com.quake.arena.logparser.application.LogService;
import com.quake.arena.logparser.domain.model.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Slf4j
@RestController
@Api
@RequestMapping("/api/log/summary")
public class LogSummaryAPI {

    @Autowired
    private LogService logService;

    @ApiOperation(value = "Returns the summary")
    @GetMapping
    public Flux<Log> get(
            @RequestParam(value = "identifier", required = false) UUID identifier,
            @RequestParam(value = "gameName", required = false) String gameName
    ) {
        return logService.findAll(identifier, gameName);
    }
}
