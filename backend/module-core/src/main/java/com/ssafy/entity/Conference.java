package com.ssafy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conference extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_category")
    private ConferenceCategory conferenceCategory;

    @Column
    private LocalDateTime callStartTime;

    @Column
    private LocalDateTime callEndTime;

    @Column
    private String thumbnailUrl;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Boolean isActive;

}