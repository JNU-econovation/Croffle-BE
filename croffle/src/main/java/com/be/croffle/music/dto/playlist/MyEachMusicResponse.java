package com.be.croffle.music.dto;

public record MyEachMusicResponse(
        Long musicId,
        String musicUrl,
        String title
) {
}
