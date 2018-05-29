package net.mj.camel.launcher.web.common.route;

import net.mj.camel.launcher.web.service.router.RouteEntity;
import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.api.management.mbean.ManagedRouteMBean;
import org.apache.camel.model.ModelHelper;
import org.apache.camel.model.RouteDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT) // server.port 설정에 따른다.
@ActiveProfiles("test")
public class RouteHelperTest {

    @Autowired
    private CamelContext camelContext;

    @Test
    public void getRouteMBeanList() throws Exception {
        List<ManagedRouteMBean> list = RouteHelper.getRouteMBeanList(camelContext);

        Assert.notNull(list);

        System.out.println("------------" + list.size());
    }

    @Test
    public void xml() throws Exception {

        List<Route> list = camelContext.getRoutes();
        list.forEach(x->{
            RouteDefinition definition = x.getRouteContext().getRoute();
            try {
                System.out.println(ModelHelper.dumpModelAsXml(camelContext, definition));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
