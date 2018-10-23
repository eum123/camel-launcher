package net.mj.camel.launcher.web.common;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class XmlUtil {

    private Document document = null;

    public XmlUtil(byte[] bytes) throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.parse(new ByteArrayInputStream(bytes));
    }

    public XmlUtil(String path) throws Exception {
        try(InputStream in = new FileInputStream(new File(path))) {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(in);
        }
    }

    public String getAttributeByXPath(String xpathString) throws Exception {

        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expression = xpath.compile(xpathString);

        return expression.evaluate(document);
    }

    /**
     *
     * @param xpathString
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public String getAttributeByXPath(String xpathString, String defaultValue) throws Exception {
        String value = getAttributeByXPath(xpathString);
        if(value == null) {
            return defaultValue;
        } else {
            return value;
        }
    }
}
