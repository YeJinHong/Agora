package com.ssafy.api.service;

import com.ssafy.api.request.FaqCommentReq;
import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.response.FaqListRes;
import com.ssafy.api.response.FaqPostRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqService {

    List<FaqListRes> selectAllFaq(Pageable pageable,String category);
    FaqPostRes selectFaqById(Long faqId);
    void createFaq(FaqPostReq faqPostReq);
    void updateFaqPost(FaqPostReq faqPostReq);

    void updateComment(FaqCommentReq faqCommentReq);

    void deleteFaq(String userEmail, Long id);
}
