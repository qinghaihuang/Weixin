package com.graduation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.graduation.po.req.TextMessage;
import com.graduation.po.resp.Article;
import com.graduation.po.resp.NewsMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "click";
	public static final String MESSAGE_VIEW = "view";

	/**
	 * @Title: xmlToMap
	 * @Description: xml转为集合
	 * @author: qinghaihuang
	 * @throws DocumentException
	 * @date: 2017年3月28日 下午4:20:34
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest req)
			throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		InputStream ins = req.getInputStream();
		Document doc = reader.read(ins);

		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		// 将遍历结果保存到list中
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		// 释放资源
		ins.close();
		ins = null;
		return map;
	}

	/**
	 * 
	 * @Title: textMessageToXml
	 * @Description: 将文本消息转化为xml对象
	 * @author: qinghaihuang
	 * @date: 2017年3月28日 下午5:39:34
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());// 需要引入xmlpull.jar包
		return xstream.toXML(textMessage);
	}

	/**
	 * 
	 * @Title: initText
	 * @Description:关键字回复设置
	 * @return: String
	 * @author: qinghaihuang
	 * @date: 2017年4月14日 下午12:50:26
	 */
	public static String initText(String toUserName, String fromUserName,
			String content) {
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}

	/**
	 * 
	 * @Title: menuText
	 * @Description: 主菜单设置及其他菜单
	 * @author: qinghaihuang
	 * @date: 2017年3月28日 下午5:24:50
	 */
	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，请按以下提示进行操作：\n\n");
		sb.append("1、平台介绍\n");
		sb.append("2、如何开始\n\n");
		sb.append("回复？进行相应操作");
		return sb.toString();
	}

	/**
	 * 
	 * @Title: firstMenu
	 * @Description: “1”消息
	 * @return: String
	 * @author: qinghaihuang
	 * @date: 2017年4月14日 下午12:28:07
	 */
	public static String firstMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("本平台旨在为美食爱好者和吃货们提供分享的平台，你可以在健康饮食平台发表文章，观看相关视频，或者和其他爱好者们一起交流！");
		return sb.toString();
	}

	/**
	 * 
	 * @Title: secondMenu
	 * @Description: “2”消息
	 * @return: String
	 * @author: qinghaihuang
	 * @date: 2017年4月14日 下午12:28:45
	 */
	public static String secondMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("您可以回复“1”或者“2”或者“？”来获取相关帮助，回复“最新”获取最新文章。另外还可以点击帮助按钮去获取客服支持!");
		return sb.toString();
	}

	/**
	 * @Title: newsMessageToXml
	 * @Description: 图文消息转换为xml
	 * @author: qinghaihuang
	 * @date: 2017年3月29日 下午2:24:43
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}

	/**
	 * 
	 * @Title: initNewsMessage
	 * @Description: 图文消息组装
	 * @author: qinghaihuang
	 * @date: 2017年4月20日 下午4:01:00
	 */
	public static String initNewsMessage(String toUserName, String fromUserName) {
		String message = null;
		List<Article> articleList = new ArrayList<Article>();
		NewsMessage newsMessage = new NewsMessage();

		Article article = new Article();
		article.setTitle("测试图文消息");
		article.setDescription("这是一篇测试文章！");
		article.setPicUrl("http://112.74.111.167/Weixin/image/350393-106.jpg");
		article.setUrl("http://112.74.111.167/library");
		// 多图文消息在此创建
		articleList.add(article);

		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(articleList);
		newsMessage.setArticleCount(articleList.size());
		message = newsMessageToXml(newsMessage);
		return message;
	}

}
