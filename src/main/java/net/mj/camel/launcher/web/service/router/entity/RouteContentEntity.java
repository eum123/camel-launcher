package net.mj.camel.launcher.web.service.router.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class RouteContentEntity {
    private String id;
    private boolean autoStartup = false;
    private String description;
    private List entitis = new LinkedList();

}
