package ir.dealit.restful.web.ws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    @EventListener
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent disconnectEvent
    ){
        log.info("disconnectEvent");
        // TODO: implement later
    }

    @EventListener
    public void handleWebSocketConnectListener(
            SessionConnectEvent connectEvent
    ){
        log.info("connectEvent");
        // TODO: implement later
    }

    @EventListener
    public void handleWebSocketConnectedListener(
            SessionConnectedEvent connectedEvent
    ){
        log.info("connectedEvent");
        // TODO: implement later
    }

    @EventListener
    public void handleWebSocketSubscribeListener(
            SessionSubscribeEvent subscribeEvent
    ){
        log.info("subscribeEvent");
        // TODO: implement later
    }

    @EventListener
    public void handleWebSocketUnsubscribeListener(
            SessionUnsubscribeEvent unsubscribeEvent
    ){
        log.info("unsubscribeEvent");
        // TODO: implement later
    }
}
