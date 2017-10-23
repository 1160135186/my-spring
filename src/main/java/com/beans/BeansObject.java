package com.beans;

import java.util.ArrayList;

public class BeansObject {
    private ArrayList<BeanObject> list = new ArrayList();
    public void add(BeanObject beanObject) {
        this.list.add(beanObject);
    }
    public ArrayList<BeanObject> getList() {
        return list;
    }
}
