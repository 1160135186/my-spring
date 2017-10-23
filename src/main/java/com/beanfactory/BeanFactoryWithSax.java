package com.beanfactory;

import com.beans.BeanDefination;
import com.parser.DomXmlParser;
import com.parser.SaxXmlHander;
import com.parser.SaxXmlParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanFactoryWithSax extends AbstractBeanFactory {
    @Override
    public void init(String xmlPath) throws Exception {
        HashMap<String,BeanDefination> map1 = new HashMap<>();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
        DefaultHandler handler = new SaxXmlHander(map1);
        SAXParser parser = new SaxXmlParser().getParser();
        parser.parse(inputStream,handler);
        map.putAll(map1);
        inject(xmlPath);
    }

    private void inject(String xmlPath) throws Exception {
        Document document = new DomXmlParser().parse(xmlPath);
        NodeList nodeList = document.getElementsByTagName("property");
        for (int i = 0;i < nodeList.getLength();++i) {
            Element element = (Element)nodeList.item(i);
            Element parentElement = (Element) element.getParentNode();
            String id = parentElement.getAttribute("id");
            String name = element.getAttribute("name");
            String ref = element.getAttribute("ref");
            BeanDefination beanDefination = map.get(id);
            Class injectClass = beanDefination.getInstance().getClass();
            for (Method method : injectClass.getMethods()) {
                if (method.getName().substring(3).toLowerCase().equals(name.toLowerCase())&&method.getName().substring(0,3).equals("set")) {
                    System.out.println("find out method");
                    method.invoke(beanDefination.getInstance(),map.get(ref).getInstance());
                }
            }
        }
    }

    // list all instance to test if lazy-init worked
    public void listAllInstance() {
        for (Map.Entry<String,BeanDefination> entry : map.entrySet()) {
            if (entry.getValue().IsInstanced()) {
                System.out.println(entry.getValue().getQuaitityedName());
            }
        }
    }
}
