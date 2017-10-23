package com.test;

import com.beanfactory.BeanFactoryWithSax;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class TestSax {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        try {
            new BeanFactoryWithSax().init("application.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
