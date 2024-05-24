package com.be.croffle.music.dto;

import lombok.Getter;

@Getter
public class GeneratedUrl {
    String success;
    RespDto response;
    String error;

    @Getter
    public static class RespDto{
        String musicURL;
    }
}
