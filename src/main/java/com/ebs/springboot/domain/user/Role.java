package com.ebs.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    SNS_USER("ROLE_SNS_USER", "SNS 사용자"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
