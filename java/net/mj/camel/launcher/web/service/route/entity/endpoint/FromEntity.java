package net.mj.camel.launcher.web.service.route.entity.endpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import net.mj.camel.launcher.web.common.route.xml.FromAttributeEnum;
import net.mj.camel.launcher.web.common.route.xml.RouteXmlTagNameConstants;
import net.mj.camel.launcher.web.service.route.entity.RouteStepEntity;
import org.jdom2.Element;

public class FromEntity implements RouteStepEntity{
    @Getter
    @JsonProperty("tagName")
    private final String tagName = RouteXmlTagNameConstants.FROM;

    @Getter
    @JsonProperty("uri")
    private String uri;

    public void read(Element element) {
        uri = element.getAttributeValue(FromAttributeEnum.URI.getKey());
    }
}
