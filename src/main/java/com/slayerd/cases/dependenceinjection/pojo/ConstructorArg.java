package com.slayerd.cases.dependenceinjection.pojo;

/**
 * 构造器注入参数
 *
 * @author slayerd
 * @since 2023/4/6
 */
public class ConstructorArg {
    private boolean isRef = false;
    private Class type;
    private Object arg;

    public boolean isRef() {
        return isRef;
    }

    public ConstructorArg(boolean isRef, Class type, Object arg) {
        if (isRef){
            if (type != null){
                throw new IllegalArgumentException("当参数是引用类型的时候,type应为空");
            }
            if (!(arg instanceof String)) {
                throw new IllegalArgumentException("当参数是引用类型的时候,arg应该指定引用BeanId");
            }
            this.isRef = isRef;
        }else {
            if (type == null || arg == null){
                throw new IllegalArgumentException("当参数是非引用类型的时候,arg和type不能为空");
            }
            this.type = type;
        }
        this.arg = arg;
    }

    public void setRef(boolean ref) {
        isRef = ref;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Object getArg() {
        return arg;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }
}
