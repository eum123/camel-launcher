package net.mj.camel.launcher.web.service.route.entity.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.mj.camel.launcher.web.common.route.xml.RouteXmlTagNameConstants;
import net.mj.camel.launcher.web.service.route.entity.RouteStepEntity;
import net.mj.camel.launcher.web.service.route.entity.RouteStepEntityFactory;
import org.jdom2.Element;

import java.util.LinkedList;
import java.util.List;

public class DoTryEntity implements RouteStepEntity {
    @Getter
    @JsonProperty("tagName")
    private String tagName = RouteXmlTagNameConstants.DOTRY;
    @Getter
    @JsonProperty("list")
    private List<RouteStepEntity> list = new LinkedList<>();

    @Override
    public void read(Element element) {
        element.getChildren().forEach(x-> {
            RouteStepEntity entity = RouteStepEntityFactory.newInstance(x);
            entity.read(x);

            list.add(entity);
        });
    }
}
