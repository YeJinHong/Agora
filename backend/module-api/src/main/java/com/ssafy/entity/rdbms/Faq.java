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
    @Column
    private String category;

    @Column
    private String content;

    @Column
    private String comment;


    @Column
    private LocalDateTime registTime;



    public void updateFaqPost(String content, String comment){
        this.content = content;
        this.comment = comment;
    }
}
