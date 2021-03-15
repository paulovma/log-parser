package com.quake.arena.logparser.infrastructure.http;

import com.quake.arena.logparser.application.LogService;
import com.quake.arena.logparser.infrastructure.http.validator.FileExtensionValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@Api
@RequestMapping("/api/log/file")
public class LogUploadAPI {

    @Autowired
    private LogService logService;

    @Autowired
    private FileExtensionValidator fileExtensionValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(fileExtensionValidator);
    }

    @ApiOperation(value = "Upload log file")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LogIdentifierResponse> post(@Validated GameLogApiRequest gameLogApiRequest) throws InterruptedException {
        MultipartFile file = gameLogApiRequest.getFile();
        log.info("M=post, I={}", file.getName());
        UUID logIdentifier = UUID.randomUUID();
        logService.read(file, logIdentifier);
        return ResponseEntity.accepted().body(new LogIdentifierResponse(logIdentifier));
    }

}
