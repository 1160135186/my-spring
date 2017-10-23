package com.beanfactory;

import com.parser.DomXmlParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BeanFactoryWithDom extends AbstractBeanFactory{

    /*
    *  find out whether there is an annotation tag in xml
    *  if true traverse the package then init the map
    *  if false then traverse the bean tag and init the map
    *  at there,we use dom to parse xml
    * */
    @Override
    public void init(String xmlPath) throws Exception {
        Document document = new DomXmlParser().parse(xmlPath);
        if (document.getElementsByTagName("scanf").getLength()>0) {
           return;
        }
        NodeList nodeList = document.getElementsByTagName("bean");
        for (int i = 0;i < nodeList.getLength();++i) {
            Element element = (Element) nodeList.item(i);
           // String className = element.getAttribute(CLASSPNAME);
            //String quatifiedName = element.getAttribute(QUATIFIEDNAME);
           // register(className,quatifiedName);
        }
    }
}
