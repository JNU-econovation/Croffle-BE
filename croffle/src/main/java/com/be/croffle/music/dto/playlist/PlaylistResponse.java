package com.be.croffle.music.dto;

import java.util.List;

public record PlaylistResponse(
        List<EachMusicResponse> music
) {
}
