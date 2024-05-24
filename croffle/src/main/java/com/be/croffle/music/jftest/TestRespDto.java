package com.be.croffle.music.jftest;

import lombok.Getter;

@Getter
public class TestRespDto {
    String status;
    String message;
    DataDto data;

    @Getter
    public static class DataDto{
        Long id;
    }
}
