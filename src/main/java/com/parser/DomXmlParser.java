package com.parser;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class DomXmlParser {

    public Document parse(String xmlPath) throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
        return builder.parse(inputStream);
    }

}
