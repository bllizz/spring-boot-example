package com.ebs.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SnsType {
    facebook("FB"), kakao("KA"), naver("NA"), gseek("GS"), google("GL");

    private final String value;

    public String getValue() {
        return this.value;
    }

    public static SnsType getSnsType(String value) {
        for(SnsType snsType : SnsType.values()) {
            if (value.equals(snsType.getValue())) {
                return snsType;
            }
        }
        return null;
    }
}
