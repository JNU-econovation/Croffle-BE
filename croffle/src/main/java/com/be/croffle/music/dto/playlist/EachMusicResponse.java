package com.be.croffle.music.dto.playlist;

public record EachMusicResponse(
        Long musicId,
        String musicUrl,
        String title,
        Long like,
        Boolean pressed
) {
}
