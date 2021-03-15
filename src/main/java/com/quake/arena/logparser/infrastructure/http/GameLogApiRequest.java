package com.quake.arena.logparser.infrastructure.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameLogApiRequest {

    private MultipartFile file;
}
