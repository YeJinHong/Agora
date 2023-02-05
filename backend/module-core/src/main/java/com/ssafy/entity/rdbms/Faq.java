package com.ssafy.entity.rdbms;

import com.ssafy.api.response.FaqPostRes;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faq extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String comment;


    @Column
    private LocalDateTime registTime;

    @Column
    private LocalDateTime updateTime;

    private void updateFaqPost(String title, String content){
        this.title = title;
        this.content = content;
    }
    private void updateFaqComment(String comment){
        this.comment = comment;
    }
    public FaqPostRes toFaqPostRes(){
        return FaqPostRes.builder()
                .id(this.getId())
                .title(this.title)
                .content(this.content)
                .userId(this.user.getUserEmail())
                .comment(this.comment)
                .registTime(this.registTime)
                .updateTime(this.updateTime)
                .build();
    }
}
