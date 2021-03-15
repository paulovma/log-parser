package com.quake.arena.logparser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Log {

    private String id;

    private UUID identifier;

    private Set<Game> games;

    private LogStatus status;
}
