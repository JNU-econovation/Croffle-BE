package com.be.croffle.music.dto.playlist;

import java.util.List;

public record MyPlaylistResponse(
        String name,
        List<MyEachMusicResponse> music
) {
}
