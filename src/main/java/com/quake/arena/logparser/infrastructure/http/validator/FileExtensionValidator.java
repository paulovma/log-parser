package com.quake.arena.logparser.infrastructure.http.validator;

import com.google.common.io.Files;
import com.quake.arena.logparser.infrastructure.http.GameLogApiRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileExtensionValidator implements Validator {

    private static final String ALLOWED_EXTENSION = "log";

    @Override
    public boolean supports(Class<?> clazz) {
        return GameLogApiRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        GameLogApiRequest gameLogApiRequest = (GameLogApiRequest) target;
        String fileExtension = Files.getFileExtension(gameLogApiRequest.getFile().getOriginalFilename());
        if (!fileExtension.equals(ALLOWED_EXTENSION)) {
            errors.reject("005", "File extension not allowed");
        }
    }
}
