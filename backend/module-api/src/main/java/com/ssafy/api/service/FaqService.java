package com.ssafy.api.service;

import com.ssafy.api.request.FaqCommentReq;
import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.request.FaqUpdateReq;
import com.ssafy.api.response.FaqListRes;
import com.ssafy.api.response.FaqPostRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqService {

    Page<FaqListRes> selectAllFaq(Pageable pageable, String category);
    FaqPostRes selectFaqById(Long faqId);
    void createFaq(FaqPostReq faqPostReq);
    void updateFaqPost(FaqUpdateReq faqUpdateReq, Long id);

    void updateComment(FaqCommentReq faqCommentReq, Long id);

    void deleteFaq(String userEmail, Long id);
}
