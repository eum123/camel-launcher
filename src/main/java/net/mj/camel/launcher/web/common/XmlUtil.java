package net.mj.camel.launcher.web.common;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;

public class XmlUtil {
    public static String getAttribute(byte[] bytes, String xpathString) throws Exception {

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document document = documentBuilder.parse(new ByteArrayInputStream(bytes));

        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expression = xpath.compile(xpathString);

        return expression.evaluate(document);
    }
}
