package net.mj.camel.launcher.web.controller;

import net.mj.camel.launcher.web.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RouterController {


    @RequestMapping(value = "/route/list", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity routeList() {
        return new ResponseEntity<>(new ApiResponse(null), HttpStatus.OK);
    }
}
