package net.mj.camel.launcher.web.common.route.xml;

import lombok.Getter;

public enum LogAttributeEnum {
    MESSAGE("message");

    @Getter
    private String key =null;

    private LogAttributeEnum(String key) {
        this.key = key;
    }
}
