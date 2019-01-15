package net.mj.camel.launcher.web.common.route.xml;

import lombok.Getter;

public enum ProcessAttributeEnum {
    REF("ref");

    @Getter
    private String key =null;

    private ProcessAttributeEnum(String key) {
        this.key = key;
    }
}
