package com.beanfactory;

import com.beans.BeanObject;
import com.beans.BeansObject;
import com.parser.DigesterXmlParser;

import java.io.InputStream;

public class BeanFactoryWithDigester extends AbstractBeanFactory {
    public void init(String xmlPath) throws Exception {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
        BeansObject beansObject = new DigesterXmlParser().parse().parse(inputStream);
        for (BeanObject beanObject : beansObject.getList()) {
            System.out.println("class-name : " + beanObject.getClassName());
            System.out.println("class-path : " + beanObject.getQuaitifiedName());
           // register(beanObject.getClassName(),beanObject.getQuaitifiedName());
        }
    }
}
