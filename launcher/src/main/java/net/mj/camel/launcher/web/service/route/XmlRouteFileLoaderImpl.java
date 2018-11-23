package net.mj.camel.launcher.web.service.route;

import lombok.Setter;
import net.mj.camel.launcher.helper.FileHelper;
import net.mj.camel.launcher.web.service.route.entity.RouteFileEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class XmlRouteFileLoaderImpl implements XmlRouteFileLoader {

    private static final Logger log = LoggerFactory.getLogger(XmlRouteFileLoaderImpl.class);

    /** 파일 이름 , entity */
    private Map<String, RouteFileEntity> routeFilesModifyTime = new ConcurrentHashMap<>();

    @Setter
    @Value("${camel.springboot.xml-routes}")
    private String routesPath;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isUpdate = false;

    public Set<String> getFileNames() throws Exception {
        try {
            lock.lock();
            if(isUpdate) {
                condition.await();
            }
            return routeFilesModifyTime.keySet();

        } finally {
            lock.unlock();
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void update() throws Exception {
        String filename = FileHelper.getFilename(routesPath);
        Path path = Paths.get(new URI(FileHelper.getOriginPath(routesPath)));

        try {

            lock.lock();
            isUpdate = true;

            Files.newDirectoryStream(path, filename).forEach(x->{
                try {
                    long modifyTime = Files.getLastModifiedTime(x, LinkOption.NOFOLLOW_LINKS).to(TimeUnit.MILLISECONDS);

                    String fileName = x.toString();

                    if (routeFilesModifyTime.containsKey(fileName)) {
                        //update
                        if(modifyTime != routeFilesModifyTime.get(fileName).getModifyTime()) {
                            routeFilesModifyTime.put(fileName, new RouteFileEntity(x, modifyTime));

                            log.info("Update : {}", x.toString());
                        }
                    } else {
                        //insert
                        routeFilesModifyTime.put(fileName, new RouteFileEntity(x, modifyTime));

                        log.info("Insert : {}", x.toString());
                    }
                } catch (Exception e) {
                    throw new RuntimeException("update fail : " + x.getFileName(), e);
                }
            });

        } finally {
            isUpdate = false;
            condition.signalAll();
            lock.unlock();
        }
    }


    /**
     * path에 해당하는 routes 파일 내용을 반환한다.
     * @param fileName path 포함
     * @return
     * @throws InterruptedException
     */
    public RouteFileEntity getRouteFileContent(String fileName) throws InterruptedException {
        try {
            lock.lock();
            if(isUpdate) {
                condition.await();
            }
            //map에서 route파일 내용들을 추출.
            if(routeFilesModifyTime.containsKey(fileName)) {
                return routeFilesModifyTime.get(fileName);
            } else {
                return null;
            }

        } finally {
            lock.unlock();
        }
    }


}
