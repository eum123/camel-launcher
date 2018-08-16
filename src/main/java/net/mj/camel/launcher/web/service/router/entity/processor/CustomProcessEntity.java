package net.mj.camel.launcher.web.service.router.entity.processor;

import lombok.Data;

@Data
public class CustomProcessEntity implements ProcessorEntity {
    private final String key = "process";
    private String ref;
}
