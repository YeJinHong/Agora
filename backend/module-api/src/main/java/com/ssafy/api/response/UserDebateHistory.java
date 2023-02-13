package com.ssafy.api.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class UserDebateHistory {

    private final String date;

    private final String title;

    private final long debateId;

    private final String role;

    private final long activeTime;

    @Builder
    public UserDebateHistory(LocalDateTime date, String title, Long debateId, String role, long activeTime) {
        this.date = date.format(DateTimeFormatter.ISO_DATE);
        this.title = title;
        this.debateId = debateId;
        this.role = role;
        this.activeTime = activeTime;
    }
}
