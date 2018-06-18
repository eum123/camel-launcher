package net.mj.camel.launcher.web.service.router;


import java.util.Map;
import java.util.Set;

public interface XmlRouteFileLoader {
    public Set<String> getFileNames() throws Exception;

    public Map<String, String> getRoutesContent(String routesId) throws InterruptedException;
}
