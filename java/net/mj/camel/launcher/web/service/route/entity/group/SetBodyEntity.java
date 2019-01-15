package net.mj.camel.launcher.web.service.route.entity.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.mj.camel.launcher.web.common.route.xml.RouteXmlConstants;
import net.mj.camel.launcher.web.common.route.xml.RouteXmlTagNameConstants;
import net.mj.camel.launcher.web.service.route.entity.RouteStepEntity;
import net.mj.camel.launcher.web.service.route.entity.RouteStepEntityFactory;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.LinkedList;
import java.util.List;

public class SetBodyEntity implements RouteStepEntity {
    @Getter
    @JsonProperty("tagName")
    private String tagName = RouteXmlTagNameConstants.SETBODY;
    @Getter
    @JsonProperty("simple")
    private String simple = "";

    @Override
    public void read(Element element) {

        Element simpleElement = element.getChild(RouteXmlTagNameConstants.SIMPLE, Namespace.getNamespace(RouteXmlConstants.DEFAULT_NAMESPACE));

        this.simple = simpleElement.getText();


    }
}
