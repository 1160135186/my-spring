package com.beanfactory;
import com.beans.BeanDefination;

import java.util.HashMap;

public abstract class AbstractBeanFactory{
        /*
          * String means the quatifiedName of bean, Boolean means if a BeanDefination references to that
          * quatifiedName has existed or not
          * use this to solve the problem that a bean have many alias
         */

        //HashMap<String,Boolean> qnameMap = new HashMap<>();

        /*
        *  use this map to have a id or name to BeanDefination mapping
        * */
        HashMap<String ,BeanDefination> map = new HashMap<>();
        //final static String CLASSPNAME = "class-name";
        //final static String QUATIFIEDNAME = "class-path";

        public void register(String IdOrName, BeanDefination beanDefination) {
            map.put(IdOrName,beanDefination);
        }

        public Object getBean(String IdOrName) {
            if (map.containsKey(IdOrName))
                return map.get(IdOrName).getInstance();
            else throw new NullPointerException("there is no such bean");
        }

        abstract public void init(String xmlPath) throws Exception;
}
