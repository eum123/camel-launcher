package net.mj.camel.launcher.web.common.route.xml;

import lombok.Getter;

public enum RoutesAttributeEnum {
    ID("id");

    @Getter
    private String key =null;

    private RoutesAttributeEnum(String key) {
        this.key = key;
    }
}
