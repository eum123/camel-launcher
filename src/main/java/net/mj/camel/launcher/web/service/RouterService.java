package net.mj.camel.launcher.web.service;

import net.mj.camel.launcher.web.service.route.entity.RouteInfoEntity;

import java.util.List;

public interface RouterService {
    public List<RouteInfoEntity> getRouteEntityList() throws Exception;

    public RouteInfoEntity getRouteEntity(String routeId) throws Exception;
}
