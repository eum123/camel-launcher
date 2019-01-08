package net.mj.camel.launcher.web.service.route.entity;

import org.jdom2.Element;

import net.mj.camel.launcher.web.common.route.xml.RouteXmlTagNameConstants;
import net.mj.camel.launcher.web.service.route.entity.endpoint.FromEntity;
import net.mj.camel.launcher.web.service.route.entity.endpoint.ToEntity;
import net.mj.camel.launcher.web.service.route.entity.group.DoTryEntity;
import net.mj.camel.launcher.web.service.route.entity.group.SetBodyEntity;
import net.mj.camel.launcher.web.service.route.entity.group.TransformEntity;
import net.mj.camel.launcher.web.service.route.entity.processor.CustomProcessEntity;
import net.mj.camel.launcher.web.service.route.entity.processor.DescriptionEntity;
import net.mj.camel.launcher.web.service.route.entity.processor.LogProcessEntity;

public class RouteStepEntityFactory {
    public static RouteStepEntity newInstance(Element element) {

        String tagName = element.getName();

        if(tagName.equals(RouteXmlTagNameConstants.FROM)) {
            return new FromEntity();
        } else if(tagName.equals(RouteXmlTagNameConstants.PROCESS)) {
            return new CustomProcessEntity();
        } else if(tagName.equals(RouteXmlTagNameConstants.TO)) {
            return new ToEntity();
        } else if(tagName.equals(RouteXmlTagNameConstants.LOG)) {
            return new LogProcessEntity();
        } else if(tagName.equals(RouteXmlTagNameConstants.DESCRIPTION)) {
            return new DescriptionEntity();
        } else if(tagName.equals(RouteXmlTagNameConstants.DOTRY)) {
            return new DoTryEntity();
        } else if(tagName.equals(RouteXmlTagNameConstants.SETBODY)) {
            return new SetBodyEntity();
        } else if(tagName.equals(RouteXmlTagNameConstants.TRANSFORM)) {
            return new TransformEntity();
        } else {
            throw new RuntimeException("unknown tag : " + tagName);
        }

    }
}
