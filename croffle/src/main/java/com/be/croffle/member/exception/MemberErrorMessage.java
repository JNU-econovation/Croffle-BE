package com.be.croffle.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorMessage {
    MEMBER_NOT_EXIST("멤버가 존재하지 않습니다.");

    private final String message;
}
