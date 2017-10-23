package com.parser;

import org.apache.commons.digester3.Digester;

public class DigesterXmlParser {
      public Digester parse() {
          Digester digester = new Digester();
          digester.addObjectCreate("beans","com.beans.BeansObject");
          digester.addObjectCreate("beans/bean","com.beans.BeanObject");
          digester.addBeanPropertySetter("beans/bean/class-name","className");
          digester.addBeanPropertySetter("beans/bean/class-path","quaitifiedName");
          digester.addSetNext("beans/bean","add");
          return digester;
      }
}
