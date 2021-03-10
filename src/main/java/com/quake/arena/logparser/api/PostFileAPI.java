package com.quake.arena.logparser.api;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Log4j2
@RestController
@RequestMapping("/api/file")
public class PostFileAPI {

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void post(@RequestParam("file") MultipartFile file) {
        log.info(file.getName());
    }


}
