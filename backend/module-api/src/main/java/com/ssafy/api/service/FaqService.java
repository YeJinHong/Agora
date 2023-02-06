package com.ssafy.api.service;

import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.request.FaqUpdateReq;
import com.ssafy.api.response.FaqPostRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FaqService {

    Page<FaqPostRes> selectAllFaq(Pageable pageable, String category);
    void createFaq(FaqPostReq faqPostReq);
    void updateFaqPost(FaqUpdateReq faqUpdateReq, Long id);
    void deleteFaq(Long id);
}
