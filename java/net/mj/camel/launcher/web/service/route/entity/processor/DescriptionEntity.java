package net.mj.camel.launcher.web.service.route.entity.processor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.mj.camel.launcher.web.common.route.xml.RouteXmlTagNameConstants;
import net.mj.camel.launcher.web.service.route.entity.RouteStepEntity;
import org.jdom2.Element;

public class DescriptionEntity implements RouteStepEntity {
    @Getter
    @JsonProperty("tagName")
    private final String tagName = RouteXmlTagNameConstants.DESCRIPTION;

    @Getter
    @JsonProperty("description")
    private String description = "";

    @Override
    public void read(Element element) {
        this.description = element.getText();
    }
}
