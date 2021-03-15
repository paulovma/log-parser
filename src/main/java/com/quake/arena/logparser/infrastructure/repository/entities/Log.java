package com.quake.arena.logparser.infrastructure.repository.entities;

import com.quake.arena.logparser.domain.model.Game;
import com.quake.arena.logparser.domain.model.LogStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("logs")
@Data
public class Log {

    @Id
    private String id;

    private UUID identifier;

    private Set<Game> games;

    private LogStatus status;
}
