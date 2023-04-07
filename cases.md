# 案例及小工具（ID生成器、高性能计数器、虚拟钱包...）

## 1.DI注入器
代码路径:com.slayerd.cases.dependenceinjection
### 1.Bean元数据类
存放每个Bean的Id,class路径,构造器参数(有参构造方法入参),是否单例,是否延迟加载
```java
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
    //省略getter/setter
}

```
### 2.构造器参数类
构造器参数,当Bean依赖别的Bean的时候,arg为依赖Bean的Id,当Bean(A)不依赖任何Bean,那么type就是对应的类名(String,int,Integer...),arg为具体数值
```java
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
        if (isRef) {
            if (type != null) {
                throw new IllegalArgumentException("当参数是引用类型的时候,type应为空");
            }
            if (!(arg instanceof String)) {
                throw new IllegalArgumentException("当参数是引用类型的时候,arg应该指定引用BeanId");
            }
            this.isRef = isRef;
        } else {
            if (type == null || arg == null) {
                throw new IllegalArgumentException("当参数是非引用类型的时候,arg和type不能为空");
            }
            this.type = type;
        }
        this.arg = arg;
    }
    //省略getter/setter
}
```
### 3.需要创建的Bean
RedisPool依赖Redis
```java
/**
 * Redis连接池
 *
 * @author slayerd
 * @since 2023/4/6
 */
@Data
@NoArgsConstructor
public class RedisPool {
    private Redis redis;

    public RedisPool(Redis redis) {
        this.redis = redis;
    }
    public void test(){
        System.out.println("DI注入成功");
    }
}

/**
 * Redis配置类
 * @author slayerd
 * @since 2023/4/6
 */
@Data
@NoArgsConstructor
public class Redis {
    private String ipAddress;
    private String port;

    public Redis(String ipAddress, String port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }
}
/**
 * 枚举类
 * @author slayerd
 * @since 2023/4/6
 */
public enum Scope {
    SINGLETON,
    PROTOTYPE

}
```
### 4.接口
#### 1.对外暴露接口
获取Bean接口
```java
/**
 *
 * @author slayerd
 * @since 2023/4/6
 */
public interface ApplicationContext {
    Object getBean(String beanId);
}
```
#### 2.解析文件接口(XML)
```java
/**
 * Bean解析接口
 * @author slayerd
 * @since 2023/4/4
 */
public interface BeanParser {
    List<BeanDefinition> parse(InputStream inputStream);
    List<BeanDefinition> parse(String configContent);
}
```

### 5.接口实现类
#### 1.对外暴露接口的实现类,提供了解析方法和getBean方法
```java
/**
 * 对外暴露的接口实现类
 *
 * @author slayerd
 * @since 2023/4/6
 */
public class XmlConfigApplicationContext implements ApplicationContext {
    private BeanFactory beanFactory;
    private XmlBeanConfigParser parser;

    public XmlConfigApplicationContext(String classPath) {
        this.beanFactory = new BeanFactory();
        this.parser = new XmlBeanConfigParser();
        loadBeanDefinition(classPath);
    }

    /**
     * 加载Bean元数据
     *
     * @param classPath xml文件路径
     */
    private void loadBeanDefinition(String classPath) {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getResourceAsStream("/" + classPath);
            if (inputStream == null) {
                throw new RuntimeException("config can not found:" + classPath);
            }
            List<BeanDefinition> beanDefinitions = parser.parse(inputStream);
            beanFactory.addBeanDefinitions(beanDefinitions);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public Object getBean(String beanId) {
        try {
            return beanFactory.getBean(beanId);
        } catch (NoSuchBeanDefinitionException e) {
            throw new RuntimeException("Bean:" + beanId + " is not found!");
        }
    }
}
```
#### 2.解析接口的实现类
在对外暴露接口实现类中的解析方法用到了此实现类的解析方法
```java
/**
 * 解析xml文件类
 *
 * @author slayerd
 * @since 2023/4/6
 */
public class XmlBeanConfigParser implements BeanParser {
    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        //省略逻辑代码
        return beanDefinitions;
    }

    /**
     * 初始化ConstructorArg List
     *
     * @param element bean标签节点
     * @return ConstructorArg List
     */
    private List<ConstructorArg> initConstructorArgs(Element element) {
        List<ConstructorArg> list = new ArrayList<>();
        //省略逻辑代码
        return list;
    }

    @Override
    public List<BeanDefinition> parse(String configContent) {
        //TODO 和上面的parse方法类似,只需要将configContent路径下的xml文件解析成InputStream流即可
        return null;
    }
}
```
### 6.工厂类
```java
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
```
### 7.大致流程
1.服务调用者通过暴露接口的实现类的构造函数传入xml文件路径

2.对外暴露接口实现类调用BeanParser实现类解析xml文件并且将需要创建的Bean的元数据(BeanDefinition)返回

3.BeanFactory加载所有的BeanDefinition,在此时,如果某个Bean不是延迟加载,那么在此时就会创建此Bean及其依赖的所有Bean

4.服务调用者调用对外暴露接口实现类中的getBean,然后会调用BeanFactory中的getBean方法,如果是单例Bean那么会判断是否在单例BeanMap中是否已经存在,如果存在了直接返回,如果不存在则会创建此Bean并且放入Map中,如果不是单例Bean,则会直接进行创建

PS:创建Bean过程会获取此Bean中的构造器参数类,然后通过反射调用其构造器方法,将构造器参数类中的参数放入,然后通过newInstance()方法创建实例.

**调用方法**
```java
/**
 * 测试DI注入
 * @author slayerd
 * @since 2023/4/6
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new XmlConfigApplicationContext("config/beans.xml");
        RedisPool redisPool = (RedisPool) applicationContext.getBean("redisPool");
        redisPool.test();
    }
}
```
