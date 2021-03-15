package com.quake.arena.logparser.infrastructure.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record LogIdentifierResponse(@JsonProperty("identifier") UUID identifier){}
