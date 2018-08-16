package net.mj.camel.launcher.web.service.router.entity.endpoint;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class FromEntity implements  EndpointEntity{
    private final String key = "from";

    private String uri;
}
