package com.example.lyn.voicechat;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by lyn on 15-6-25.
 */
public class SaxService {
    public SaxService() {
        // TODO Auto-generated constructor stub
    }

    public static List<HashMap<String, String>> readXML(InputStream inputStream,String nodeName)
    {
        try {
            //实例化SAX工厂类
            SAXParserFactory factory=SAXParserFactory.newInstance();
            //实例化SAX解析器。
            SAXParser sParser=factory.newSAXParser();
            //实例化DefaultHandler，设置需要解析的节点
            XmlHandler xmlHandler=new XmlHandler(nodeName);
            // 开始解析
            sParser.parse(inputStream, xmlHandler);
            // 解析完成之后，关闭流
            inputStream.close();
            //返回解析结果。
            return xmlHandler.getList();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

}
