package net.mj.camel.launcher.web.controller;

import net.mj.camel.launcher.web.common.ApiResponse;
import net.mj.camel.launcher.web.service.router.RouteEntity;
import net.mj.camel.launcher.web.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RouteController {

    private static final Logger log = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouterService routerService;

    @RequestMapping(value = "/routes", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity routeList() throws Exception {

        try {
            List<RouteEntity> list = routerService.getRouteEntityList();

            return new ResponseEntity<>(new ApiResponse(list), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }

    }

    @RequestMapping(value = "/route/{routeId}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity getRoute(@PathVariable String routeId) throws Exception {
        try {
            RouteEntity entity = routerService.getRouteEntity(routeId);

            return new ResponseEntity<>(new ApiResponse(entity), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * route file 목록
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/routeFiles", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity getRouteFiles() throws Exception {

        return null;
    }
}
