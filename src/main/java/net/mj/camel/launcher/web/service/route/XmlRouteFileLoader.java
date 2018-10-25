package net.mj.camel.launcher.web.service.route;


import net.mj.camel.launcher.web.service.route.entity.RouteFileEntity;

import java.util.Map;
import java.util.Set;

public interface XmlRouteFileLoader {
    public Set<String> getFileNames() throws Exception;

    /**
     * 파일 내용을 구한다
     * @param fileName
     * @return
     * @throws InterruptedException
     */
    public RouteFileEntity getRouteFileContent(String fileName) throws InterruptedException;
}
