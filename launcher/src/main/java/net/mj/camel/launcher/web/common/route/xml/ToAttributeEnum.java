package net.mj.camel.launcher.web.common.route.xml;

import lombok.Getter;

public enum ToAttributeEnum {
    URI("uri");

    @Getter
    private String key =null;

    private ToAttributeEnum(String key) {
        this.key = key;
    }
}
