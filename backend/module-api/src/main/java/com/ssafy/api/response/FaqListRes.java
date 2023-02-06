package com.ssafy.api.response;


import com.ssafy.entity.rdbms.Faq;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqListRes {
    private Long id;

    private String userId;

    private String category;

    public FaqListRes of(Faq faq) {
        return  FaqListRes.builder()
                .id(faq.getId())
                .userId(faq.getUser().getUserEmail())
                .category(faq.getCategory())
                .build();
    }
}




