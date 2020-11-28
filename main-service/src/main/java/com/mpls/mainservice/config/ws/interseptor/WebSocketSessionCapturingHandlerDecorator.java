package com.mpls.mainservice.config.ws.interseptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

public class WebSocketSessionCapturingHandlerDecorator extends WebSocketHandlerDecorator {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketSessionCapturingHandlerDecorator.class);

	public WebSocketSessionCapturingHandlerDecorator(WebSocketHandler delegate) {
		super(delegate);
	}

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("afterConnectionEstablished");
		logger.info(session.toString());
		super.afterConnectionEstablished(session);
	}

	public void handleMessage(WebSocketSession session,
							  @NonNull WebSocketMessage<?> message) throws Exception {
		logger.info("handleMessage");
		logger.info(session.toString());
		super.handleMessage(session, message);
	}

	public void handleTransportError(WebSocketSession session,
									 @NonNull Throwable exception) throws Exception {
		logger.info("handleTransportError");
		logger.info(session.toString());
		super.handleTransportError(session, exception);
	}

	public void afterConnectionClosed(WebSocketSession session,
									  @NonNull CloseStatus closeStatus) throws Exception {
		logger.info("afterConnectionClosed");
		logger.info(session.toString());
		super.afterConnectionClosed(session, closeStatus);
	}
}
