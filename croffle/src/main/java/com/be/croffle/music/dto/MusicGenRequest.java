package com.be.croffle.music.dto;

public record MusicGenRequest(
        Integer speed,
        String mood,
        String loc,

        String date
) {

}
