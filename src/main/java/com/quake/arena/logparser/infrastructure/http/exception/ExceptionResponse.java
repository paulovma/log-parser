package com.quake.arena.logparser.infrastructure.http.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ExceptionResponse (@JsonProperty("errors") List<String> message) {}
