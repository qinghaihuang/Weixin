package com.graduation.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;

import com.graduation.util.MessageUtil;

public class WeixinService {
	public static String HandleRequest(HttpServletRequest req)
			throws IOException {
		String message = null;
		try {
			// 服务器接收到用户发来的xml请求消息
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			// String message = "success";

			// 被动回复
			if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
				if ("1".equals(content)) {
					message = MessageUtil.initText(toUserName, fromUserName,
							MessageUtil.firstMenu());
				} else if ("2".equals(content)) {
					message = MessageUtil.initText(toUserName, fromUserName,
							MessageUtil.secondMenu());
				} else if ("?".equals(content) || "？".equals(content)) {
					message = MessageUtil.initText(toUserName, fromUserName,
							MessageUtil.menuText());
				} else if ("3".equals(content)) {
					message = MessageUtil.initNewsMessage(toUserName,
							fromUserName);
				}
			}

			// if ("text".equals(msgType)) {
			// TestMessage text = new TestMessage();
			// text.setToUserName(fromUserName);
			// text.setFromUserName(toUserName);
			// text.setMsgType("text");
			// text.setCreateTime(new Date().getTime());
			// text.setContent("您已发送消息为" + content);
			// message = MessageUtil.textMessageToXml(text);

			else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
				String eventType = map.get("Event");
				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
					message = MessageUtil.initText(toUserName, fromUserName,
							MessageUtil.menuText());
				}
			}
			System.out.print("用户请求数据：\n" + map + "\n\n" + "回复数据：\n" + message);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return message;
	}
}
