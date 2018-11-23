package net.mj.camel.launcher.web.service;

import net.mj.camel.launcher.web.service.route.entity.RouteInfoEntity;
import net.mj.camel.launcher.web.service.route.RouterServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // server.port 설정에 따른다.
@ActiveProfiles("test")
public class RouterSerivceTest {

    private static final Logger log = LoggerFactory.getLogger(RouterSerivceTest.class);

    @Autowired(required = true)
    private RouterServiceImpl routerService;

    @Test
    public void getRouteEntityList() throws Exception {

        List<RouteInfoEntity> list = routerService.getRouteEntityList();
        Assert.notNull(list);

        Assert.notEmpty(list);

        list.forEach(x->{
            Assert.notNull(x.getRouteId());
        });
    }

    @Test
    public void getRouteEntity() throws Exception {

        RouteInfoEntity entity = routerService.getRouteEntity("process");
        Assert.notNull(entity);



    }
}
