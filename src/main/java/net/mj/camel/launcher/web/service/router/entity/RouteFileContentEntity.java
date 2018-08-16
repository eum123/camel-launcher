package net.mj.camel.launcher.web.service.router.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.List;

public class RouteFileContentEntity {
    @JsonProperty("routesId")
    private String routesId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("route")
    private List<RouteContentEntity> routeEntity;

}
