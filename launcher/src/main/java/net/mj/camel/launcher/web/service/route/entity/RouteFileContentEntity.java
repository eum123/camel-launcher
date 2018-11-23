package net.mj.camel.launcher.web.service.route.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RouteFileContentEntity {
    @JsonProperty("routesId")
    private String routesId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("route")
    private List<RouteEntity> routeEntity;

}
