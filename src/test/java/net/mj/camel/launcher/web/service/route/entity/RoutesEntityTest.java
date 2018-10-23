package net.mj.camel.launcher.web.service.route.entity;

import net.mj.camel.launcher.web.service.route.entity.endpoint.FromEntity;
import net.mj.camel.launcher.web.service.route.entity.endpoint.ToEntity;
import net.mj.camel.launcher.web.service.route.entity.processor.DescriptionEntity;
import net.mj.camel.launcher.web.service.route.entity.processor.LogProcessEntity;
import org.junit.Assert;
import org.junit.Test;

public class RoutesEntityTest {
    @Test
    public void read() throws Exception {

        RoutesEntity entity = new RoutesEntity();
        entity.read(System.getProperty("user.dir") + "/src/test/resources/conf/router/general.xml");

        Assert.assertEquals(entity.getRoutesId(), "camel");

        Assert.assertNotNull(entity.getRouteList());
        Assert.assertEquals(entity.getRouteList().size(), 1);

        entity.getRouteList().forEach(x->{

            Assert.assertEquals(x.getId(), "general");
            Assert.assertEquals(x.isAutoStartup(), false);

            Assert.assertEquals(x.getList().size(), 4);

            x.getList().forEach(y -> {
                if(y instanceof DescriptionEntity) {
                    descriptionTest((DescriptionEntity)y);
                } else if(y instanceof FromEntity) {
                    fromTest((FromEntity)y);
                } else if(y instanceof ToEntity) {
                    toTest((ToEntity)y);
                } else if(y instanceof LogProcessEntity) {
                    logTest((LogProcessEntity)y);
                }
            });
        });
    }

    public void descriptionTest(DescriptionEntity entity) {
        Assert.assertNotNull(entity.getDescription());
    }

    public void fromTest(FromEntity entity) {
        Assert.assertNotNull(entity.getUri());
    }

    public void toTest(ToEntity entity) {
        Assert.assertNotNull(entity.getUri());
    }

    public void logTest(LogProcessEntity entity) {
        Assert.assertNotNull(entity.getMessage());
    }
}
