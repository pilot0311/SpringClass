package org.doit.ik.ws;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EchoHandler extends TextWebSocketHandler{

	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.printf("%s 연결됨\n",session.getId());
		users.put(session.getId(), session);
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.printf("%s로 부터 [%s]받음\n",session.getId(),message.getPayload());
		
		for(WebSocketSession s : users.values()) {
			s.sendMessage(message);
			System.out.println(s.getId()+"에 메세지 발송");
		}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		System.out.printf("%s로 부터 연결 끊김\n",session.getId());
		users.remove(session.getId());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("예외 발생" + exception.getMessage());
	}

	
	
}
