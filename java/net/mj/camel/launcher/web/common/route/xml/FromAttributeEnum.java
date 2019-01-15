package net.mj.camel.launcher.web.common.route.xml;

import lombok.Getter;

public enum FromAttributeEnum {
    URI("uri");

    @Getter
    private String key =null;

    private FromAttributeEnum(String key) {
        this.key = key;
    }
}
