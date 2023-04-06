package com.slayerd.cases.dependenceinjection.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean的元数据
 * @author slayerd
 * @since 2023/4/6
 */
public class BeanDefinition {
    private String id;
    private String clazzName;
    private List<ConstructorArg> constructorArgs;
    private Scope scope = Scope.SINGLETON;
    private boolean lazyInit = false;

    public BeanDefinition(String id, String clazzName, List<ConstructorArg> constructorArgs, Scope scope, boolean lazyInit) {
        this.id = id;
        this.clazzName = clazzName;
        this.constructorArgs = constructorArgs;
        this.scope = scope;
        this.lazyInit = lazyInit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public boolean isSingleton(){
        return scope == Scope.SINGLETON;
    }
}
