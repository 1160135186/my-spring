package com.beans;

public class BeanDefination {
    private String id ;
    private String quaitityedName;
    private String name;
    private ScopeType scopeType;
    private boolean lazy_init;
    private Object instance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuaitityedName() {
        return quaitityedName;
    }

    public void setQuaitityedName(String quaitityedName) {
        this.quaitityedName = quaitityedName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScopeType getScopeType() {
        return scopeType;
    }

    public void setScopeType(ScopeType scopeType) {
        this.scopeType = scopeType;
    }

    public boolean isLazy_init() {
        return lazy_init;
    }

    public void setLazy_init(boolean lazy_init) {
        this.lazy_init = lazy_init;
    }

    public void setInstance() {
        try {
            this.instance = Class.forName(this.getQuaitityedName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Object getInstance() {
        try {
            System.out.println("test----- " + scopeType.getType());
            if (this.scopeType.getType().equals("prototype")){
                System.out.println("prototype");
                return Class.forName(this.quaitityedName).newInstance();
            }
            else if (instance == null) instance = Class.forName(this.quaitityedName).newInstance();
         } catch (Exception e) {
            e.printStackTrace();
        }
        return this.instance;
    }



    // add this method just for test
    public boolean IsInstanced() {
        if (instance == null) return false;

        return true;
    }

}
