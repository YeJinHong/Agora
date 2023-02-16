package com.ssafy.api.response;

import com.ssafy.entity.rdbms.Faq;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqPostRes {

    private Long id;
    private String category;
    private String content;
    private LocalDateTime registTime;

    private String comment;

    public Page<FaqPostRes> toDtoList(Page<Faq> faqPage){
        Page<FaqPostRes> faqListResList = faqPage.map(faq -> FaqPostRes.builder()
                .id(faq.getId())
                .content(faq.getContent())
                .comment(faq.getComment())
                .registTime(faq.getRegistTime())
                .category(faq.getCategory())
                .build());
        return faqListResList;
    }
}
