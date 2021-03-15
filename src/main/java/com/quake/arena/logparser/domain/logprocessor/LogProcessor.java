package com.quake.arena.logparser.domain.logprocessor;


import com.quake.arena.logparser.domain.model.Log;

import java.io.BufferedReader;
import java.util.UUID;

public interface LogProcessor {

    Log process(BufferedReader bufferedReader, UUID logIdentifier);
}
