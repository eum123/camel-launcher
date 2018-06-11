package net.mj.camel.launcher.web.service.router;

import lombok.Data;
import net.mj.camel.launcher.helper.FileHelper;
import net.mj.camel.launcher.web.common.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class XmlRouteFileLoaderImpl implements XmlRouteFileLoader {

    private static final Logger log = LoggerFactory.getLogger(XmlRouteFileLoaderImpl.class);

    /** routes , entity */
    private Map<String, RouteFileEntity> routeFilesModifyTime = new ConcurrentHashMap<>();

    @Value("${camel.springboot.xml-routes}")
    private String routesPath;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isUpdate = false;

    @Scheduled(fixedDelay = 5000)
    public void update() throws Exception {
        String pathString = removeWildcard(routesPath);

        URI uri = new URI(pathString);

        Path path = Paths.get(uri);

        try {

            lock.lock();

            isUpdate = true;

            Files.list(path).forEach(x->{

                try {

                    long modifyTime = Files.getLastModifiedTime(x, LinkOption.NOFOLLOW_LINKS).to(TimeUnit.MILLISECONDS);
                    byte[] bytes = Files.readAllBytes(x);
                    String fileName = x.getFileName().toString();

                    if (routeFilesModifyTime.containsKey(fileName)) {
                        //update
                        if(modifyTime != routeFilesModifyTime.get(fileName).getModifyTime()) {
                            routeFilesModifyTime.put(fileName, new RouteFileEntity(x, fileName, modifyTime));

                            log.info("Update : {}", x.toString());
                        }
                    } else {
                        //insert
                        routeFilesModifyTime.put(fileName, new RouteFileEntity(x, fileName, modifyTime));

                        log.info("Insert : {}", x.toString());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        } catch(Exception e) {

        } finally {
            isUpdate = false;
            condition.signalAll();
            lock.unlock();
        }
    }


    /**
     * RouteId에 해당하는 xml파일 내용을 구한다
     * @param routesId
     * @return
     */
    public Map<String, String> getRoutesContent(String routesId) throws InterruptedException {

        try {
            lock.lock();
            if(isUpdate) {
                condition.await();
            }
            //map에서 route파일 내용들을 추출.
            if(routeFilesModifyTime.containsKey(routesId)) {
                return routeFilesModifyTime.get(routesId).getRouteContents();
            } else {
                return null;
            }

        } finally {
            lock.unlock();
        }
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
        private Path path;
        private String routesId;
        private long modifyTime;

        /** routeId, file 내용 */
        private Map<String, String> routeContents = new TreeMap();

        protected RouteFileEntity(Path path, String routesId, long modifyTime ) {
            this.path = path;
            this.routesId = routesId;
            this.modifyTime = modifyTime;
        }

        public void addRouteContent(String routeId, String contents) {
            routeContents.put(routeId, contents);
        }
    }
}
