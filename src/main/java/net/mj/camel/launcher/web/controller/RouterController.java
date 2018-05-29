package net.mj.camel.launcher.web.controller;

import net.mj.camel.launcher.web.common.ApiResponse;
import net.mj.camel.launcher.web.service.router.RouteEntity;
import net.mj.camel.launcher.web.service.RouterService;
import net.mj.camel.launcher.web.service.router.RouterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RouterController {

    private static final Logger log = LoggerFactory.getLogger(RouterController.class);

    @Autowired
    private RouterService routerService;

    @RequestMapping(value = "/route/list", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity routeList() throws Exception {

        try {
            List<RouteEntity> list = routerService.getRouterList();

            return new ResponseEntity<>(new ApiResponse(list), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }

    }
}
