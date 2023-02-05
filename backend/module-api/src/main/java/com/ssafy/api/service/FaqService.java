package com.ssafy.api.service;

import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.response.FaqListRes;
import com.ssafy.api.response.FaqPostRes;

import java.util.List;

public interface FaqService {

    List<FaqListRes> selectAllFaq(int pageNum);
    FaqPostRes selectFaqById(String faqId);
    void createFaq(FaqPostReq faqPostReq);
    void updateFaqPost(FaqPostReq faqPostReq);

    void updateComment(String comment);

    void deleteFaq(FaqPostReq faqPostReq);
}
