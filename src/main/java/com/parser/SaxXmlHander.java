package com.parser;

import com.beans.BeanDefination;
import com.beans.ScopeType;
import com.sun.beans.finder.BeanInfoFinder;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.HashMap;


public class SaxXmlHander extends DefaultHandler {
    private HashMap<String,BeanDefination> map;

    public SaxXmlHander(HashMap<String,BeanDefination> map) {
        this.map = map;
    }
    @Override
    public void startDocument() throws SAXException {
        System.out.println("开始解析xml");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.println("开始解析元素");
        if (qName.equals("bean")) {
            BeanDefination beanDefination = attributesToBean(attributes);
            if (!beanDefination.isLazy_init()) {
                System.out.println("非延迟加载");
                beanDefination.setInstance();
                if (beanDefination.getId()!=null) {
                    map.put(beanDefination.getId(),beanDefination);
                }
                if (beanDefination.getName()!=null) {
                    String[] strings = beanDefination.getName().split(",");
                    for (String string : strings) {
                        map.put(string,beanDefination);
                    }
                }
            }
        }


        // read property tag to inject bean into another bean

        if ("property".equals(qName)) {
            String name = attributes.getValue("name");
            String ref = attributes.getValue("ref");
            BeanDefination beanDefination = map.get(ref);


        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.println("解析元素结束");

    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("停止解析xml");
    }

    public BeanDefination attributesToBean(Attributes attributes) {
        /*
        *  1. first, use xml config to init beanDefinati
        * */

        // first step
        BeanDefination beanDefination = new BeanDefination();
        beanDefination.setId(attributes.getValue("id"));
        beanDefination.setName(attributes.getValue("name"));
        String lazyInitText = attributes.getValue("lazy-init");
        Boolean lazyInit = "true".equals(lazyInitText)?true:false;
        System.out.println("lazyInit " + lazyInitText );
        beanDefination.setLazy_init(lazyInit);
        beanDefination.setQuaitityedName(attributes.getValue("class"));
        String scopeTypeText = attributes.getValue("scope");
        if ("prototype".equals(scopeTypeText))
            beanDefination.setScopeType(ScopeType.PROTOTYPE);
        else beanDefination.setScopeType(ScopeType.SIGLETON);
        return beanDefination;
    }
}
