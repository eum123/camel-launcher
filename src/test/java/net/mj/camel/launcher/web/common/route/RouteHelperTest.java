package net.mj.camel.launcher.web.common.route;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Route;
import org.apache.camel.api.management.mbean.ManagedRouteMBean;
import org.apache.camel.converter.jaxp.XmlConverter;
import org.apache.camel.model.ModelHelper;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.RoutesDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.w3c.dom.Document;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT) // server.port 설정에 따른다.
@ActiveProfiles("test")
public class RouteHelperTest {

    @Autowired
    private CamelContext camelContext;

    @Test
    public void getRouteMBeanList() throws Exception {
        List<ManagedRouteMBean> list = RouteHelper.getRouteMBeanList(camelContext);

        Assert.notNull(list);

    }

    @Test
    public void getRouteMBean() throws Exception {
        ManagedRouteMBean bean = RouteHelper.getRouteMBean(camelContext, "process");
        System.out.println(bean.toString());
        Assert.notNull(bean);

    }

    @Test
    public void xml() throws Exception {

        List<Route> list = camelContext.getRoutes();
        list.forEach(x->{
            RouteDefinition definition = x.getRouteContext().getRoute();
            try {
                System.out.println(ModelHelper.dumpModelAsXml(camelContext, definition));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void xmlWrite() throws Exception {

        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<routes id=\"camel\" xmlns=\"http://camel.apache.org/schema/spring\">\n" +
                "\t<description> routes 정보 </description>\n" +
                "\n" +
                "\t<route id=\"process\" autoStartup=\"true\">\n" +
                "\t\t<description>description.... 처음에 있어야함</description>\n" +
                "\n" +
                "\t\t<from uri=\"timer:hello?period=5000\" />\n" +
                "\n" +
                "\t\t<log message=\"Message111:${body}\" />\n" +
                "\n" +
                "\t\t<process ref=\"sample\" />\n" +
                "    \n" +
                "\t\t<to uri=\"log:cool\" />\n" +
                "\n" +
                "\t</route>\n" +
                "\n" +
                "\t<route id=\"process1\" autoStartup=\"true\">\n" +
                "\t\t<description>description.... 처음에 있어야함</description>\n" +
                "\n" +
                "\t\t<from uri=\"timer:hello?period=5000\" />\n" +
                "\n" +
                "\t\t<log message=\"Message111:${body}\" />\n" +
                "\n" +
                "\t\t<process ref=\"sample\" />\n" +
                "\n" +
                "\t\t<to uri=\"log:cool\" />\n" +
                "\n" +
                "\t</route>\n" +
                "\n" +
                "</routes>\n";


        RoutesDefinition definition = ModelHelper.loadRoutesDefinition(camelContext, new ByteArrayInputStream(xmlString.getBytes()));


        System.out.println(definition.getRoutes().size());


        XmlConverter converter = new XmlConverter();
        Document dom = converter.toDOMDocument(new ByteArrayInputStream(xmlString.getBytes()), (Exchange)null);

    }

    @Test
    public void readXml() throws Exception {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<routes id=\"camel\" xmlns=\"http://camel.apache.org/schema/spring\">\n" +
                "\t<description> routes 정보 </description>\n" +
                "\n" +
                "\t<route id=\"process\" autoStartup=\"true\">\n" +
                "\t\t<description>description.... 처음에 있어야함</description>\n" +
                "\n" +
                "\t\t<from uri=\"timer:hello?period=5000\" />\n" +
                "\n" +
                "\t\t<log message=\"Message111:${body}\" />\n" +
                "\n" +
                "\t\t<process ref=\"sample\" />\n" +
                "    \n" +
                "\t\t<to uri=\"log:cool\" />\n" +
                "\n" +
                "\t</route>\n" +
                "\n" +
                "\t<route id=\"process1\" autoStartup=\"true\">\n" +
                "\t\t<description>description.... 처음에 있어야함</description>\n" +
                "\n" +
                "\t\t<from uri=\"timer:hello?period=5000\" />\n" +
                "\n" +
                "\t\t<log message=\"Message111:${body}\" />\n" +
                "\n" +
                "\t\t<process ref=\"sample\" />\n" +
                "\n" +
                "\t\t<to uri=\"log:cool\" />\n" +
                "\n" +
                "\t</route>\n" +
                "\n" +
                "</routes>\n";


        XmlConverter converter = new XmlConverter();
        Document dom = converter.toDOMDocument(new ByteArrayInputStream(xmlString.getBytes()), (Exchange)null);

        System.out.println(dom.getElementsByTagName("routes").item(0).getAttributes().getNamedItem("id").getNodeValue());
    }


}
