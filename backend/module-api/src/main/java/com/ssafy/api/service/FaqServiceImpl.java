package com.ssafy.api.service;

import com.ssafy.api.request.FaqCommentReq;
import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.request.FaqUpdateReq;
import com.ssafy.api.response.FaqListRes;
import com.ssafy.api.response.FaqPostRes;
import com.ssafy.entity.rdbms.Faq;
import com.ssafy.entity.rdbms.User;
import com.ssafy.repository.FaqRepository;
import com.ssafy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService{

    private final FaqRepository faqRepository;
    private final UserRepository userRepository;



    @Override
    public Page<FaqListRes> selectAllFaq(Pageable pageable,String category) {
        Page<Faq> faqList = faqRepository.findAllByCategory(category,pageable);
        Page<FaqListRes> faqListRes = new FaqListRes().toDtoList(faqList);
        return faqListRes;
    }

    @Override
    public FaqPostRes selectFaqById(Long faqId) {
        Faq faq = faqRepository.findById(faqId).orElseThrow(NoSuchElementException::new);
        return FaqPostRes.builder()
                .id(faq.getId())
                .userId(faq.getUser().getUserEmail())
                .category(faq.getCategory())
                .content(faq.getContent())
                .comment(faq.getComment())
                .registTime(faq.getRegistTime())
                .updateTime(faq.getUpdateTime())
                .build();
    }

    @Override
    public void createFaq(FaqPostReq faqPostReq) {

        User user = userRepository.findByUserEmail(faqPostReq.getUserEmail()).orElseThrow(NoSuchElementException::new);

        Faq faq = Faq.builder()
                .category(faqPostReq.getCategory())
                .content(faqPostReq.getContent())
                .user(user)
                .registTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        faqRepository.save(faq);
    }

    @Override
    public void updateFaqPost(FaqUpdateReq faqUpdateReq, Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(NoSuchElementException::new);
        faq.updateFaqPost(faqUpdateReq.getContent());
        faqRepository.save(faq);

    }

    @Override
    public void updateComment(FaqCommentReq faqCommentReq, Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(NoSuchElementException::new);

        faq.updateFaqComment(faqCommentReq.getComment());

        faqRepository.save(faq);
    }

    @Override
    public void deleteFaq(String userEmail, Long id) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(NoSuchElementException::new);
        Faq faq = faqRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(!user.getUserEmail().equals(faq.getUser().getUserEmail())) {
            throw new RuntimeException("권한이 없습니다.");
        }
        faqRepository.deleteById(id);
    }
}
