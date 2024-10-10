package com.be.croffle.music.dto.playlist;

public record MyEachMusicResponse(
        Long musicId,
        String musicUrl,
        String title
) {
}
