package net.mj.camel.launcher.web.service.route.entity.processor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import net.mj.camel.launcher.web.common.route.xml.RouteXmlTagNameConstants;
import net.mj.camel.launcher.web.service.route.entity.RouteStepEntity;
import org.jdom2.Element;

public class CustomProcessEntity implements ProcessorEntity, RouteStepEntity {
    @Getter
    @JsonProperty("tagName")
    private final String tagName = RouteXmlTagNameConstants.PROCESS;

    @Getter
    @JsonProperty("ref")
    private String ref;

    @Override
    public void read(Element element) {

    }
}
