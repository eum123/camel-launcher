package net.mj.camel.launcher.web.service.router.entity.endpoint;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ToEntity {
    private final String key = "to";

    private String uri;

}
