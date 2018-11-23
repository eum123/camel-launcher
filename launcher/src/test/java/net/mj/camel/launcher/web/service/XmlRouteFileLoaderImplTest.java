package net.mj.camel.launcher.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.mj.camel.launcher.web.service.route.XmlRouteFileLoaderImpl;
import net.mj.camel.launcher.web.service.route.entity.RouteFileEntity;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XmlRouteFileLoaderImplTest {

    private static final Logger log = LoggerFactory.getLogger(XmlRouteFileLoaderImplTest.class);


    @Test
    public void getRouteEntityList() throws Exception {

        String path = "file://" +System.getProperty("user.dir") + "/src/test/resources/conf/route/";

        XmlRouteFileLoaderImpl loader = new XmlRouteFileLoaderImpl();
        loader.setRoutesPath(path);

        loader.update();


        Assert.assertEquals(Files.list(Paths.get(new URI(path))).count(), loader.getFileNames().size());

    }

    @Test
    public void routeFileContent() throws Exception {
        String path = "file://" +System.getProperty("user.dir") + "/src/test/resources/conf/route/";

        XmlRouteFileLoaderImpl loader = new XmlRouteFileLoaderImpl();
        loader.setRoutesPath(path);

        loader.update();


        Files.list(Paths.get(new URI(path))).forEach(x -> {

            try {
                RouteFileEntity entity = loader.getRouteFileContent(x.toString());
                Assert.assertNotEquals(entity.getModifyTime(), 0);

                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);  //pretty view

                System.out.println(mapper.writeValueAsString(entity.getRoutesEntity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
