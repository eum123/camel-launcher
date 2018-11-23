package net.mj.camel.launcher.web.common.route.xml;

import lombok.Getter;

public enum RouteAttributeEnum {
    ID("id"),
    AUTOSTARTUP("autoStartup");

    @Getter
    private String key =null;

    private RouteAttributeEnum(String key) {
        this.key = key;
    }
}
