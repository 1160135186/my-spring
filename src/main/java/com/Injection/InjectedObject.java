package com.Injection;

public class InjectedObject {
    public String symbol = "root";
    private IBaseObject iBaseObject;

    public void setiBaseObject(IBaseObject iBaseObject) {
        this.iBaseObject = iBaseObject;
    }

    public IBaseObject getiBaseObject() {
        return iBaseObject;
    }
}
