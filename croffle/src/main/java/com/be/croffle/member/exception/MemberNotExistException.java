package com.be.croffle.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberNotExistException extends RuntimeException {
    private final MemberErrorMessage memberErrorMessage;
}
