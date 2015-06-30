package com.example.lyn.voicechat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

/**
 * Created by lyn on 15-6-25.
 */
public class XmlHandler extends DefaultHandler{
    private List<HashMap<String,String>> list = null;
    private HashMap<String, String> map = null;
    private String currentTag = null;
    private String currentValue = null;
    private String nodeName = null;

    public XmlHandler(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public void startDocument() throws SAXException {
        // 接收文档开始的通知。
        // 实例化ArrayList用于存放解析XML后的数据
        list = new ArrayList<HashMap<String, String>>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                         Attributes attributes) throws SAXException {
        // 接收元素开始的通知。
        if (qName.equals(nodeName)) {
            //如果当前运行的节点名称与设定需要读取的节点名称相同，则实例化HashMap
            map = new HashMap<String, String>();
        }
        //Attributes为当前节点的属性值，如果存在属性值，则属性值也读取。

        if (attributes != null && map != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                //读取到的属性值，插入到Map中。
                map.put(attributes.getQName(i), attributes.getValue(i));
            }

    }
    //记录当前节点的名称。
    currentTag = qName;

}

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // 接收元素中字符数据的通知。
        //当前节点有值的情况下才继续执行
        if (currentTag != null && map != null) {
            //获取当前节点的文本值，ch这个直接数组就是存放的文本值。
            currentValue = new String(ch, start, length);
            if (currentValue != null && !currentValue.equals("")
                    && !currentValue.equals("\n")) {
                //读取的文本需要判断不能为null、不能等于”“、不能等于”\n“
                map.put(currentTag, currentValue);
            }
        }
        //读取完成后，需要清空当前节点的标签值和所包含的文本值。
        currentTag = null;
        currentValue = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // 接收元素结束的通知。
        if (qName.equals(nodeName)) {
            //如果读取的结合节点是我们需要关注的节点，则把map加入到list中保存
            list.add(map);
            //使用之后清空map，开始新一轮的读取person。
            map = null;
        }
    }

    public List<HashMap<String, String>> getList() {
        return list;
    }
}
