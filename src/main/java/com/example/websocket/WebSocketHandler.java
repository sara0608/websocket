package com.example.websocket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
	Map<WebSocketSession, String> user = new HashMap<WebSocketSession, String>();
	
	/* 클라이언트로부터 메시지 수신시 동작 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		JSONObject receiveMessage = new JSONObject(message.getPayload());
		if(receiveMessage.get("type").equals("login")) {
			user.put(session, (String) receiveMessage.get("id"));
		}
		Iterator<WebSocketSession> keys = user.keySet().iterator();
		while( keys.hasNext() ){
			WebSocketSession strKey = keys.next();
			if(strKey != session) {
				strKey.sendMessage(new TextMessage(message.getPayload()));	
			}
		}
		System.out.println(receiveMessage);
	}
	
	/* 클라이언트가 소켓 연결시 동작 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	}
	
	/* 클라이언트가 소켓 종료시 동작 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		user.remove(session);
	}
}
