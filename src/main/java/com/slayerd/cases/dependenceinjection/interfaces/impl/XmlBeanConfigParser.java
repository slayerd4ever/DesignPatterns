package com.slayerd.cases.dependenceinjection.interfaces.impl;

import com.slayerd.cases.dependenceinjection.pojo.BeanDefinition;
import com.slayerd.cases.dependenceinjection.interfaces.BeanParser;
import com.slayerd.cases.dependenceinjection.pojo.ConstructorArg;
import com.slayerd.cases.dependenceinjection.pojo.Scope;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse(inputStream);
            //normalize格式化xml文件,原因:https://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.normalizeDocument();
            //根据标签bean获取节点(node)List
            NodeList beans = doc.getElementsByTagName("bean");
            for (int i = 0; i < beans.getLength(); i++) {
                Node item = beans.item(i);
                if (item.getNodeType() != Node.ELEMENT_NODE) continue;

                Element element = (Element) item;

                //初始化ConstructorArg List
                List<ConstructorArg> constructorArgs = initConstructorArgs(element);

                BeanDefinition beanDefinition = new BeanDefinition(
                        element.getAttribute("id"),
                        element.getAttribute("class"),
                        constructorArgs,
                        "singleton".equals(element.getAttribute("scope")) ? Scope.SINGLETON : Scope.PROTOTYPE,
                        "true".equals(element.getAttribute("lazy_init")) ? true : false
                );
                beanDefinitions.add(beanDefinition);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
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
        NodeList argList = element.getElementsByTagName("constructor-arg");
        for (int i = 0; i < argList.getLength(); i++) {
            Node item = argList.item(i);
            if (item.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            element = (Element) item;
            if (!element.getAttribute("ref").isEmpty()) {
                ConstructorArg constructorArg = new ConstructorArg(true, null, element.getAttribute("ref"));
                list.add(constructorArg);
            } else if (!element.getAttribute("type").isEmpty()) {
                ConstructorArg constructorArg = new ConstructorArg(false, String.class, element.getAttribute("value"));
                list.add(constructorArg);
            }
        }
        return list;
    }

    @Override
    public List<BeanDefinition> parse(String configContent) {
        //TODO 和上面的parse方法类似,只需要将configContent路径下的xml文件解析成InputStream流即可
        return null;
    }
}
