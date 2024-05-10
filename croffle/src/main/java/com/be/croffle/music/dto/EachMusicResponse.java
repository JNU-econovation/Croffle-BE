package com.be.croffle.music.dto;

public record EachMusicResponse(
        Long musicId,
        String musicUrl,
        String title
) {
}
