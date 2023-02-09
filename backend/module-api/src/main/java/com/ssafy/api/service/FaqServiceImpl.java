package com.ssafy.api.service;

import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.request.FaqUpdateReq;
import com.ssafy.api.response.FaqPostRes;
import com.ssafy.entity.rdbms.Faq;
import com.ssafy.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService{

    private final FaqRepository faqRepository;



    @Override
    public Page<FaqPostRes> selectAllFaq(Pageable pageable,String category) {
        Page<Faq> faqList = faqRepository.findAllByCategory(category,pageable);
        Page<FaqPostRes> faqListRes = new FaqPostRes().toDtoList(faqList);
        return faqListRes;
    }

    @Override
    public void createFaq(FaqPostReq faqPostReq) {

        Faq faq = Faq.builder()
                .category(faqPostReq.getCategory())
                .content(faqPostReq.getContent())
                .comment(faqPostReq.getComment())
                .registTime(LocalDateTime.now())
                .build();

        faqRepository.save(faq);
    }

    @Override
    public void updateFaqPost(FaqUpdateReq faqUpdateReq, Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(NoSuchElementException::new);
        faq.updateFaqPost(faqUpdateReq.getContent(), faqUpdateReq.getComment());
        faqRepository.save(faq);

    }

    @Override
    public void deleteFaq(Long id) {
        faqRepository.deleteById(id);
    }
}
