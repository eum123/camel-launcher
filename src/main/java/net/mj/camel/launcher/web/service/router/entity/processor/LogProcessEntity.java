package net.mj.camel.launcher.web.service.router.entity.processor;

import lombok.Data;

@Data
public class LogProcessEntity implements ProcessorEntity {
    private final String key = "log";
    private String message = "";
}
