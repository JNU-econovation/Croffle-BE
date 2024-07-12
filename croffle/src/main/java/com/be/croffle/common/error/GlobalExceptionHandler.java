package com.be.croffle.common.error;

import com.be.croffle.common.utils.ApiResponse;
import com.be.croffle.common.utils.ApiResponseGenerator;
import com.be.croffle.member.exception.MemberErrorMessage;
import com.be.croffle.member.exception.MemberNotExistException;
import com.be.croffle.music.exception.MusicErrorMessage;
import com.be.croffle.music.exception.MusicNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MusicNotExistException.class)
    public ApiResponse<ApiResponse.CustomBody> handleIllegalStateException(MusicNotExistException e){
        return ApiResponseGenerator.fail(MusicErrorMessage.MUSIC_NOT_EXIST.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberNotExistException.class)
    public ApiResponse<ApiResponse.CustomBody> handleIllegalStateException(MemberNotExistException e){
        return ApiResponseGenerator.fail(MemberErrorMessage.MEMBER_NOT_EXIST.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
