package com.ssafy.domain;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Collection;

public interface Room {
    String getRoomName();

    void startCountDown(Participant user);

    void pauseCountDown(Participant user);

    Collection<Participant> getParticipants();

    void leave(Participant user) throws IOException;

    void close();

    Participant join(String userName, String position, WebSocketSession session, boolean isScreen) throws IOException;
}
