package com.be.croffle.music.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MusicNotExistException extends RuntimeException {
    private final MusicErrorMessage musicErrorMessage;
}
