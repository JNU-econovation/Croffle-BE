package com.be.croffle.music.dto.gen;

import com.be.croffle.music.dto.gen.GeneratedUrlResponse;

public record ServerResponse(
        String success,
        GeneratedUrlResponse response,
        String error
) {
}
