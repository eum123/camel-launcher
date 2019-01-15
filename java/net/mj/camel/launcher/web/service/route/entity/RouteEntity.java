package net.mj.camel.launcher.web.service.route.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.mj.camel.launcher.web.common.route.xml.RouteAttributeEnum;
import net.mj.camel.launcher.web.common.route.xml.RouteXmlTagNameConstants;
import org.jdom2.Element;

import java.util.LinkedList;
import java.util.List;

public class RouteEntity {
    @Getter
    @JsonProperty("tagName")
    private String tagName = RouteXmlTagNameConstants.ROUTE;
    @Getter
    @JsonProperty("id")
    private String id;
    @Getter
    @JsonProperty("autoStartup")
    private boolean autoStartup = false;
    @Getter
    @JsonProperty("list")
    private List<RouteStepEntity> list = new LinkedList<>();

    public void read(Element element) {

        this.id = element.getAttributeValue(RouteAttributeEnum.ID.getKey());
        this.autoStartup = Boolean.parseBoolean(element.getAttributeValue(RouteAttributeEnum.AUTOSTARTUP.getKey(), "true"));

        element.getChildren().forEach(x-> {
            RouteStepEntity entity = RouteStepEntityFactory.newInstance(x);
            entity.read(x);

            list.add(entity);
        });
    }

}
