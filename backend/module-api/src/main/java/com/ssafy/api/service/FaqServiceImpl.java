package com.ssafy.api.service;

import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.response.FaqListRes;
import com.ssafy.api.response.FaqPostRes;
import com.ssafy.entity.rdbms.Faq;
import com.ssafy.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService{

    private final FaqRepository faqRepository;

    @Override
    public List<FaqListRes> selectAllFaq(int pageNum) {
        List<Faq> faqList = faqRepository.findAll();
        return null;
    }

    @Override
    public FaqPostRes selectFaqById(String faqId) {
        Faq faq = faqRepository.findById(Long.getLong(faqId)).orElseThrow(NoSuchElementException::new);
        return faq.toFaqPostRes();
    }

    @Override
    public void createFaq(FaqPostReq faqPostReq) {

    }

    @Override
    public void updateFaqPost(FaqPostReq faqPostReq) {

    }

    @Override
    public void updateComment(String comment) {

    }

    @Override
    public void deleteFaq(FaqPostReq faqPostReq) {

    }
}
