package com.be.croffle.music.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MusicErrorMessage {

    MUSIC_NOT_EXIST("음악이 존재하지 않습니다.");

    private final String message;
}
