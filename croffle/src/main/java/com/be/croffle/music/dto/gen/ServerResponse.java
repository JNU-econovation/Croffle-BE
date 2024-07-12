package com.be.croffle.music.dto;

public record ServerResponse(
        String success,
        GeneratedUrlResponse response,
        String error
) {
}
