package net.mj.camel.launcher.web.service;

import net.mj.camel.launcher.helper.FileHelper;
import net.mj.camel.launcher.web.service.route.entity.RouteFileEntity;
import net.mj.camel.launcher.web.service.route.XmlRouteFileLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // server.port 설정에 따른다.
@ActiveProfiles("test")
public class XmlRouteFileLoaderImplTest {

    private static final Logger log = LoggerFactory.getLogger(XmlRouteFileLoaderImplTest.class);

    @Value("${camel.springboot.xml-routes}")
    private String routesPath;

    @Autowired(required = true)
    private XmlRouteFileLoader service;

    @Test
    public void getRouteEntityList() throws Exception {


        Assert.assertNotNull(service.getFileNames());


        String path = FileHelper.getOriginPath(routesPath);

        Assert.assertEquals(Files.list(Paths.get(new URI(path))).count(), service.getFileNames().size());

    }

    @Test
    public void routeFileContent() throws Exception {
        String path = FileHelper.getOriginPath(routesPath);

        Files.list(Paths.get(new URI(path))).forEach(x -> {

            try {
                RouteFileEntity entity = service.getRouteFileContent(x.getFileName().toString());
                Assert.assertNotEquals(entity.getModifyTime(), 0);
                Assert.assertNotNull(entity.getRouteContents());
                Assert.assertNotEquals(entity.getRouteContents().size(), 0);
            } catch (Exception e) {

            }
        });
    }

}
