package net.mj.camel.launcher.web.service.route;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.api.management.mbean.ManagedRouteMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mj.camel.launcher.web.common.route.RouteHelper;
import net.mj.camel.launcher.web.service.RouterService;
import net.mj.camel.launcher.web.service.route.entity.RouteInfoEntity;

@Service
public class RouterServiceImpl implements RouterService {
    private static final Logger log = LoggerFactory.getLogger(RouterServiceImpl.class);

    @Autowired
    private CamelContext camelContext;

    public List<RouteInfoEntity> getRouteEntityList() throws Exception {


        List<RouteInfoEntity> list = new ArrayList();

        
        RouteHelper.getRouteMBeanList(camelContext).forEach(x->{

            //TODO : 파일 이름 추가
            RouteInfoEntity entity = new RouteInfoEntity("", x.getRouteId());

            try {
                entity.setExchangesInflight(x.getExchangesInflight());
                entity.setExchangesFailed(x.getExchangesFailed());
                entity.setDescription(x.getDescription());
                entity.setExchangesTotal(x.getExchangesTotal());
                entity.setLastProcessingTime(x.getLastProcessingTime());
                entity.setMaxProcessingTime(x.getMaxProcessingTime());
                entity.setMinProcessingTime(x.getMinProcessingTime());
                entity.setStartTimestamp(x.getStartTimestamp());
                entity.setUptimeMills(x.getUptimeMillis());
                entity.setState(x.getState());

                list.add(entity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

        return list;
    }



    public RouteInfoEntity getRouteEntity(String routeId) throws Exception {

        ManagedRouteMBean x = RouteHelper.getRouteMBean(camelContext, routeId);
        if(x != null) {
            //TODO : 파일 이름 추가
            RouteInfoEntity entity = new RouteInfoEntity("", x.getRouteId());

            try {
                entity.setExchangesInflight(x.getExchangesInflight());
                entity.setExchangesFailed(x.getExchangesFailed());
                entity.setDescription(x.getDescription());
                entity.setExchangesTotal(x.getExchangesTotal());
                entity.setLastProcessingTime(x.getLastProcessingTime());
                entity.setMaxProcessingTime(x.getMaxProcessingTime());
                entity.setMinProcessingTime(x.getMinProcessingTime());
                entity.setStartTimestamp(x.getStartTimestamp());
                entity.setUptimeMills(x.getUptimeMillis());
                entity.setState(x.getState());

                return entity;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return null;


    }
}
