package net.mj.camel.launcher.web.service.route.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.mj.camel.launcher.web.common.route.xml.RouteXmlConstants;
import net.mj.camel.launcher.web.common.route.xml.RouteXmlTagNameConstants;
import net.mj.camel.launcher.web.common.route.xml.RoutesAttributeEnum;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class RoutesEntity {
    @Getter
    @JsonProperty("routesId")
    private String routesId = null;
    @Getter
    @JsonProperty("routes")
    private List<RouteEntity> routeList = new LinkedList<RouteEntity>();

    public void read(String path) throws Exception {
        try (InputStream in = new FileInputStream(new File(path))) {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(in);

            Element routesElement = document.getRootElement();
            routesId = routesElement.getAttributeValue(RoutesAttributeEnum.ID.getKey());

            Namespace namespace = Namespace.getNamespace(RouteXmlConstants.DEFAULT_NAMESPACE);

            routesElement.getChildren(RouteXmlTagNameConstants.ROUTE, namespace).forEach(x -> {
                RouteEntity entity = new RouteEntity();
                entity.read(x);

                routeList.add(entity);
            });

        }
    }
}
