package com.test;

import com.Injection.InjectedObject;
import com.beans.Student;
import com.beanfactory.AbstractBeanFactory;
import com.beanfactory.BeanFactoryWithSax;
import org.junit.Test;

public class TestBeanFactory {

    @Test
    public  void testSingleton() throws Exception {
        AbstractBeanFactory beanFactory = new BeanFactoryWithSax();
        beanFactory.init("application.xml");
        // when the scope type is singleton , answer is true
        // when the scope type is prototype , answer is false
        Student student = (Student) beanFactory.getBean("student");
        Student student1 = (Student) beanFactory.getBean("student");
        System.out.println(student == student1);
    }

    @Test
    public void testLazyInit() throws Exception {
        BeanFactoryWithSax beanFactory = new BeanFactoryWithSax();
        beanFactory.init("application.xml");
        beanFactory.listAllInstance();
    }

    @Test
    public void testGetBeanWithDifferentString() throws Exception {
        AbstractBeanFactory beanFactory = new BeanFactoryWithSax();
        beanFactory.init("application.xml");

        // use id or different names to get the same bean
        // this test case must be under the circumstance that scope type is single or default

        Student student1 = (Student)beanFactory.getBean("student");// id
        Student student2 = (Student)beanFactory.getBean("studentName");//studentName
        Student student3 = (Student)beanFactory.getBean("aliaName");// aliaName

        System.out.println(student1 == student2);
        System.out.println(student2 == student3);
    }

    @Test
    public void testInject() throws Exception {
        BeanFactoryWithSax beanFactory = new BeanFactoryWithSax();
        beanFactory.init("application.xml");
        InjectedObject injectedObject = (InjectedObject)beanFactory.getBean("InjectedObject");
        injectedObject.getiBaseObject().say();
    }
}
