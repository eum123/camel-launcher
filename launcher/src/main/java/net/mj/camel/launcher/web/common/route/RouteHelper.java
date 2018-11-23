package net.mj.camel.launcher.web.common.route;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.api.management.mbean.ManagedRouteMBean;
import org.apache.camel.management.mbean.ManagedRoute;

import java.util.ArrayList;
import java.util.List;

public class RouteHelper {
    public static List<ManagedRouteMBean> getRouteMBeanList(CamelContext camelContext) throws Exception {
        List<ManagedRouteMBean> routeEntityList = new ArrayList();
        camelContext.getRoutes().forEach(x -> {
            routeEntityList.add(camelContext.getManagedRoute(x.getId(), ManagedRouteMBean.class));
        });

        return routeEntityList;
    }

    public static ManagedRouteMBean getRouteMBean(CamelContext camelContext, String routeId) throws Exception {
        return camelContext.getManagedRoute(routeId, ManagedRouteMBean.class);
    }
}
