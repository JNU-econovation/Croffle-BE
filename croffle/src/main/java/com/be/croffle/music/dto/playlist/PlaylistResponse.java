package com.be.croffle.music.dto.playlist;

import java.util.List;

public record PlaylistResponse(
        List<EachMusicResponse> music
) {
}
