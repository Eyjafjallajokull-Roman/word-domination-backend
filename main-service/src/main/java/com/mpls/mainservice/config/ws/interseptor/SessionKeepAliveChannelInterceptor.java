package com.mpls.mainservice.config.ws.interseptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SessionKeepAliveChannelInterceptor extends ChannelInterceptorAdapter {

	@Autowired
	private SessionRepository<Session> sessionRepository;

	private static final Logger logger = LoggerFactory.getLogger(SessionKeepAliveChannelInterceptor.class);


	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		Map<String, Object> sessionHeaders = SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders());
		assert sessionHeaders != null;
		String sessionId = (String) sessionHeaders.get(WSConstants.SESSION_ATTR);
		if (sessionId != null) {
			Session session = sessionRepository.findById(sessionId);
			if (session != null) {
				logger.info("Keeping session with id : " + sessionId + " alive ");
				sessionRepository.save(session);
			}
		}
		return super.preSend(message, channel);
	}

	@Autowired
	public void setSessionRepository(SessionRepository<Session> sessionRepository) {
		this.sessionRepository = sessionRepository;
	}
}
