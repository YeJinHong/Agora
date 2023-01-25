package com.ssafy.entity.rdbms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debate_id")
    private Debate debate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mvp_id")
    private User mvpUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perspective_id")
    private Perspective perspective;
}
