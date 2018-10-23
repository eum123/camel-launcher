package net.mj.camel.launcher.web.service.route.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

@Data
public class RouteFileEntity {

    private Path path;

    @JsonProperty("routesId")
    private String routesId;

    @JsonProperty("modifiyTime")
    private long modifyTime;

    /** routeId, file 내용 */
    @JsonProperty("routeList")
    private Map<String, String> routeContents = new TreeMap();

    public RouteFileEntity(Path path, String routesId, long modifyTime ) {
        this.path = path;
        this.routesId = routesId;
        this.modifyTime = modifyTime;
    }

    public void addRouteContent(String routeId, String contents) {
        routeContents.put(routeId, contents);
    }
}
