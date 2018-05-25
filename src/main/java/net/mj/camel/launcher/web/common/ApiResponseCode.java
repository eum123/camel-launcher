package net.mj.camel.launcher.web.common;

import lombok.Getter;

public enum ApiResponseCode {
    OK("정상"),
    INTERNAL_ERROR("내부 오류 발생");

    @Getter
    private String message;

    ApiResponseCode(String message) {
        this.message = message;
    }
}
