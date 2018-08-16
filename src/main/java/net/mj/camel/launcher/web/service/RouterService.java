package net.mj.camel.launcher.web.service;

import net.mj.camel.launcher.web.service.router.entity.RouteInfoEntity;

import java.util.List;

public interface RouterService {
    public List<RouteInfoEntity> getRouteEntityList() throws Exception;

    public RouteInfoEntity getRouteEntity(String routeId) throws Exception;
}
