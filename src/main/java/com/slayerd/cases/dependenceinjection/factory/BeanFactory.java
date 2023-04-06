package com.slayerd.cases.dependenceinjection.factory;

import com.slayerd.cases.dependenceinjection.pojo.BeanDefinition;
import com.slayerd.cases.dependenceinjection.pojo.ConstructorArg;
import com.slayerd.exceptions.NoSuchBeanDefinitionException;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂类
 *
 * @author slayerd
 * @since 2023/4/6
 */
public class BeanFactory {
    private ConcurrentHashMap<String, Object> singletonBeans = new ConcurrentHashMap<String, Object>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<String, BeanDefinition>();

    /**
     * list判空
     * @param list
     * @return
     * @param <T>
     */
    private <T> List<T> safe(List<T> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        return list;
    }

    /**
     * 添加Bean元数据,如果不是延迟加载就在此时就创建Bean
     *
     * @param beanDefinitions
     */
    public void addBeanDefinitions(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : safe(beanDefinitions)) {
            this.beanDefinitions.putIfAbsent(beanDefinition.getId(), beanDefinition);
        }
        for (BeanDefinition beanDefinition : safe(beanDefinitions)) {
            if (!beanDefinition.isLazyInit()) {
                creatBean(beanDefinition);
            }
        }
    }

    /**
     * 创建Bean
     *
     * @param beanDefinition
     */
    private Object creatBean(BeanDefinition beanDefinition) {
        if (beanDefinition.isSingleton() && this.singletonBeans.containsKey(beanDefinition.getId())) {
            return this.singletonBeans.get(beanDefinition.getId());
        }
        Object bean = null;
        try {
            List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
            Class<?> beanClazz = Class.forName(beanDefinition.getClazzName());
            if (constructorArgs.isEmpty()) {
                bean = beanClazz.newInstance();
            }
            Class[] argClasses = new Class[constructorArgs.size()];
            Object[] argObjects = new Object[constructorArgs.size()];
            for (int i = 0; i < constructorArgs.size(); i++) {
                ConstructorArg constructorArg = constructorArgs.get(i);
                if (!constructorArg.isRef()) {
                    //不是引用类型,只需要将bean标签中的type(type)和value(arg)放入数组即可
                    argClasses[i] = constructorArg.getType();
                    argObjects[i] = constructorArg.getArg();
                }else {
                    //是引用类型,则需要判断元数据List中是否存在引用的bean的元数据,然后将bean标签中引用的Bean的class对象放入class数组,将引用Bean元数据创建后的实例化对象放入Object数组
                    BeanDefinition refBeanDefinition = beanDefinitions.get(constructorArg.getArg());
                    if (refBeanDefinition == null){
                        throw new NoSuchBeanDefinitionException("Bean is not Defined:" + constructorArg.getArg());
                    }
                    argClasses[i] = Class.forName(refBeanDefinition.getClazzName());
                    argObjects[i] = creatBean(refBeanDefinition);
                }
            }
            bean = beanClazz.getConstructor(argClasses).newInstance(argObjects);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        } finally {
            if (bean != null && beanDefinition.isSingleton()){
                this.singletonBeans.putIfAbsent(beanDefinition.getId(),bean);
                return this.singletonBeans.get(beanDefinition.getId());
            }
            return bean;
        }
    }

    /**
     * 获取Bean
     *
     * @param beanId Bean的Id
     * @return 实例化的Bean对象
     * @throws NoSuchBeanDefinitionException Bean未被定义异常
     */
    public Object getBean(String beanId) throws NoSuchBeanDefinitionException {
        BeanDefinition beanDefinition = this.beanDefinitions.get(beanId);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException("Bean is not defined:" + beanId);
        }
        return creatBean(beanDefinition);
    }
}


