package net.mj.camel.launcher.web.service.route.entity.processor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.mj.camel.launcher.web.common.route.xml.LogAttributeEnum;
import net.mj.camel.launcher.web.common.route.xml.RouteXmlTagNameConstants;
import net.mj.camel.launcher.web.service.route.entity.RouteStepEntity;
import org.jdom2.Element;

public class LogProcessEntity implements ProcessorEntity, RouteStepEntity {
    @Getter
    @JsonProperty("tagName")
    private final String tagName = RouteXmlTagNameConstants.LOG;
    @Getter
    @JsonProperty("message")
    private String message = "";

    @Override
    public void read(Element element) {
        message = element.getAttributeValue(LogAttributeEnum.MESSAGE.getKey(), "");
    }
}
