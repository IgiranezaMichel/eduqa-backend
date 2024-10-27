package com.eduqa_backend.configuration.websocket;

import java.security.Principal;
import java.util.Map;

import org.apache.catalina.connector.CoyotePrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class UserHandShakeHandler extends DefaultHandshakeHandler{

    @SuppressWarnings("null")
    @Override
    @Nullable
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
            Map<String, Object> attributes) {
                log.info("session : ",request.getPrincipal());
        return new CoyotePrincipal(request.getPrincipal().getName());
    }

}

