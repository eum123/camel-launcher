package net.mj.camel.launcher.web.service.router;


import net.mj.camel.launcher.web.service.router.entity.RouteFileEntity;

import java.util.Map;
import java.util.Set;

public interface XmlRouteFileLoader {
    public Set<String> getFileNames() throws Exception;

    public Map<String, String> getRoutesContent(String routesId) throws InterruptedException;

    /**
     * 파일 내용을 구한다
     * @param fileName
     * @return
     * @throws InterruptedException
     */
    public RouteFileEntity getRouteFileContent(String fileName) throws InterruptedException;
}
