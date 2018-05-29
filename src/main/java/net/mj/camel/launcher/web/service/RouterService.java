package net.mj.camel.launcher.web.service;

import net.mj.camel.launcher.web.service.router.RouteEntity;

import java.util.List;

public interface RouterService {
    public List<RouteEntity> getRouterList() throws Exception;
}
