package com.beta.prop.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * 
 * @ClassName:  XmlConvertHandler   
 * @Description:(xml与Java对象之间的转换)   
 * @author: zouyao
 * @date:   2017年6月27日 下午1:26:54   
 *     
 * @Copyright: 2017 
 *
 */
public class XmlConvertHandler<T> {

    //日志打印对象
    private  Logger  log  =  LoggerFactory.getLogger(XmlConvertHandler.class);
    
    
    /**
     * 
     * @Title: domConvert   
     * @Description: (xml转换到对象)   
     * @param: @param entityClass
     * @param: @param input
     * @param: @param entityTagName
     * @param: @return      
     * @return: List<T>      
     * @throws
     */
    public  List<T>  domConvert(Class<T>  entityClass  ,  InputStream  input ,  String entityTagName){
        log.debug("开始解析Xml");
        List<T>  result =  null;
        
        DocumentBuilderFactory  factory  =  DocumentBuilderFactory.newInstance();
        
        DocumentBuilder builder  = null;
        
        Document  document =  null;
        try {
            builder = factory.newDocumentBuilder();
            document  =  builder.parse(input);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        
        result = this.splitElementToBind(entityClass, document , entityTagName);
        return result;
    }
    
    
    public  List<T>  domConvert(Class<T>  entityClass  ,  InputSource  input ,  String entityTagName){
        log.debug("开始解析Xml");
        List<T>  result =  null;
        
        DocumentBuilderFactory  factory  =  DocumentBuilderFactory.newInstance();
        
        DocumentBuilder builder  = null;
        
        Document  document =  null;
        try {
            builder = factory.newDocumentBuilder();
            document  =  builder.parse(input);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        
        result = this.splitElementToBind(entityClass, document , entityTagName);
        return result;
    }
    
    /**
     * 
     * @Title: splitElementToBind   
     * @Description: (将元素拆分并获取值)   
     * @param: @param entityClass
     * @param: @param entityList
     * @param: @return      
     * @return: List<T>      
     * @throws
     */
    private  List<T>  splitElementToBind(Class<T>  entityClass,Document  document , String entityTagName){
    	List<T>  result = new ArrayList<T>();
        Element  rootElement = document.getDocumentElement();
        
        NodeList  entityList  = rootElement.getElementsByTagName(entityTagName);
        if(entityList != null){
            //获取到域数组
            Field[] fields = entityClass.getDeclaredFields();
            
            for(int i = 0 ; i < entityList.getLength() ; i++){
                T obj = null;
                
                try {
                    obj = entityClass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                
                Node node = entityList.item(i);
                
                NodeList  nodeList = node.getChildNodes();
                
                this.bindNodeListToObject(obj, fields, nodeList);
                
                result.add(obj);
            }
        }
    	
    	return result;
    	
    }
    /**
     * 
     * @Title: bindNodeListToObject   
     * @Description: (将XML节点与JavaBean进行绑定)   
     * @param: @param entityClass
     * @param: @return      
     * @return: T      
     * @throws
     */
    private  void  bindNodeListToObject(Object obj,  Field[] fields,  NodeList  nodeList){
        if(obj != null &&  nodeList!=null &&  fields!= null){
            for(int i = 0 ; i < nodeList.getLength() ; i++){
                
                Node node  = nodeList.item(i);
                String  tagName = node.getNodeName().toLowerCase();
                String value = node.getFirstChild() ==null ? null :node.getFirstChild().getNodeValue();
                if(value == null){
                    continue;
                }
                for(int j = 0 ; j < fields.length ; j++){
                    Field field = fields[j];
                    
                    String  fieldName =  field.getName().toLowerCase();
                    
                    if(tagName.equals(fieldName)){
                        try {
                            
                            if (field.isAccessible()) {
                                field.set(obj, value);
                                
                            } else {
                                field.setAccessible(true);
                                field.set(obj, value);
                                field.setAccessible(false);
                            }
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
    }
    
}
