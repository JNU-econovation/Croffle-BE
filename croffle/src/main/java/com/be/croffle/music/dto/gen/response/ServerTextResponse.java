package com.be.croffle.music.dto.gen.response;

public record ServerTextResponse(
        String success,
        GeneratedUrlTextResponse response,
        String error
) {
}
