package net.mj.camel.launcher.web.service.route.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

@Data
public class RouteFileEntity {

    @JsonProperty("path")
    private Path path;

    @JsonProperty("modifiyTime")
    private long modifyTime;

    /** routeId, file 내용 */
    @JsonProperty("routes")
    private RoutesEntity routesEntity = null;

    public RouteFileEntity(Path path, long modifyTime ) throws Exception {
        this.path = path;
        this.modifyTime = modifyTime;
        this.routesEntity = loadRoutesFile(path);
    }

    private RoutesEntity loadRoutesFile(Path path) throws Exception {
        RoutesEntity entity = new RoutesEntity();
        entity.read(path.toString());

        return entity;
    }

}
