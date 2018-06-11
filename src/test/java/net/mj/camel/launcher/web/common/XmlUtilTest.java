package net.mj.camel.launcher.web.common;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlUtilTest {
    @Test
    public void test() throws Exception {

        byte[] bytes = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<routes id=\"cameldkdkd\" xmlns=\"http://camel.apache.org/schema/spring\">\n" +
                "\n" +
                "\n" +
                "\t<route id=\"general\" autoStartup=\"false\">\n" +
                "\t\t<description>description.... 처음에 있어야함</description>\n" +
                "\n" +
                "\t\t<from uri=\"timer:hello?period=5000\" />\n" +
                "\n" +
                "\t\t<log message=\"Message:${body}\" />\n" +
                "    \n" +
                "\t\t<to uri=\"log:cool\" />\n" +
                "\n" +
                "\t</route>\n" +
                "\n" +
                "</routes>\n").getBytes();

        Assert.assertEquals("cameldkdkd", XmlUtil.getAttribute(bytes, "/routes/@id"));

    }
}
