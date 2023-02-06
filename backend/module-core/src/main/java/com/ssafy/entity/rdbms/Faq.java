package com.ssafy.entity.rdbms;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Faq extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String category;

    @Column
    private String content;

    @Column
    private String comment;


    @Column
    private LocalDateTime registTime;

    @Column
    private LocalDateTime updateTime;


    public void updateFaqPost(String content){
        this.content = content;
    }
    public void updateFaqComment(String comment){
        this.comment = comment;
    }

}
