package net.mj.camel.launcher.web.service.router;

import lombok.Data;
import net.mj.camel.launcher.helper.FileHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class XmlRouteFileLoaderImpl implements XmlRouteFileLoader {
    private Map<String, RouteFileEntity> routeFilesModifyTime = new ConcurrentHashMap<>();

    @Value("camel.springboot.xml-routes")
    private String routesPath;

    @Scheduled(fixedDelay = 5000)
    public void update() throws Exception {
        String pathString = removeWildcard(routesPath);

        URI uri = new URI(pathString);

        Path path = Paths.get(uri);
    }

    /**
     * RouteId에 해당하는 xml파일 내용을 구한다
     * @param routeId
     * @return
     */
    public String getRouteContent(String routeId) {
        return null;
    }

    private String removeWildcard(String path) {
        if(path != null && path.lastIndexOf("/") < path.length()) {
            return path.substring(0, path.lastIndexOf("/") + 1);
        } else {
            return path;
        }
    }


    @Data
    class RouteFileEntity {
        private String path;
        private String contents;
        private long modifyTime;

        protected RouteFileEntity(String path, String contents, long modifyTime ) {

        }
    }
}
