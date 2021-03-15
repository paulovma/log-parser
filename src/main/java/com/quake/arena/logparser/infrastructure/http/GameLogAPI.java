package com.quake.arena.logparser.infrastructure.http;

import com.quake.arena.logparser.application.LogService;
import com.quake.arena.logparser.domain.model.Log;
import com.quake.arena.logparser.infrastructure.http.validator.FileExtensionValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Slf4j
@RestController
@Api(value = "TESTE")
@RequestMapping("/api/file")
public class GameLogAPI {

    @Autowired
    private LogService logService;

    @Autowired
    private FileExtensionValidator fileExtensionValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(fileExtensionValidator);
    }

    @ApiOperation(value = "Returns the information for the indications screen")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LogIdentifierResponse> post(@RequestParam("file") MultipartFile file) throws InterruptedException {
        log.info("M=post, I={}", file.getName());
        UUID logIdentifier = UUID.randomUUID();
        logService.read(file, logIdentifier);
        return ResponseEntity.accepted().body(new LogIdentifierResponse(logIdentifier));
    }

    @ApiOperation(value = "Returns the information for the indications screen")
    @GetMapping
    public Flux<Log> get(
        @RequestParam(value = "identifier", required = false) UUID identifier,
        @RequestParam(value = "gameName", required = false) String gameName
    ) {
        return logService.findAll(identifier, gameName);
    }

}
