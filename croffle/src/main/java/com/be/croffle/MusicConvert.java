package com.be.croffle;

import com.be.croffle.music.Music;
import com.be.croffle.music.dto.EachMusicResponse;

public class MusicConvert {
    public static EachMusicResponse convertToEachMusicResponse(Music music) {
        return new EachMusicResponse(
                music.getId(),
                music.getMusicUrl()
        );
    }
}
