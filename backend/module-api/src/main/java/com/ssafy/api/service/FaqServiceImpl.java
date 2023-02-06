package com.ssafy.api.service;

import com.ssafy.api.request.FaqCommentReq;
import com.ssafy.api.request.FaqPostReq;
import com.ssafy.api.response.FaqListRes;
import com.ssafy.api.response.FaqPostRes;
import com.ssafy.entity.rdbms.Faq;
import com.ssafy.entity.rdbms.User;
import com.ssafy.repository.FaqRepository;
import com.ssafy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public List<FaqListRes> selectAllFaq(Pageable pageable) {
        List<Faq> faqList = faqRepository.findAllByPage(pageable);
        List<FaqListRes> faqListResList = new ArrayList<>();
        for(int i = 0; i<faqList.size(); i++) {
            faqListResList.add(new FaqListRes().of(faqList.get(i)));
        }
        return faqListResList;
    }

    @Override
    public FaqPostRes selectFaqById(String faqId) {
        Faq faq = faqRepository.findById(Long.getLong(faqId)).orElseThrow(NoSuchElementException::new);
        return FaqPostRes.builder()
                .userId(faq.getUser().getUserEmail())
                .title(faq.getTitle())
                .content(faq.getContent())
                .comment(faq.getComment())
                .registTime(faq.getRegistTime())
                .updateTime(faq.getUpdateTime())
                .build();
    }

    @Override
    public void createFaq(FaqPostReq faqPostReq) {

        User user = userRepository.findByUserEmail(faqPostReq.getUserId()).orElseThrow(NoSuchElementException::new);

        Faq faq = Faq.builder()
                .title(faqPostReq.getTitle())
                .content(faqPostReq.getContent())
                .user(user)
                .registTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        faqRepository.save(faq);
    }

    @Override
    public void updateFaqPost(FaqPostReq faqPostReq) {
        Faq faq = faqRepository.findById(faqPostReq.getId()).orElseThrow(NoSuchElementException::new);
        faq.updateFaqPost(faqPostReq.getTitle(),faqPostReq.getContent());
        faqRepository.save(faq);

    }

    @Override
    public void updateComment(FaqCommentReq faqCommentReq) {
        Faq faq = faqRepository.findById(faqCommentReq.getId()).orElseThrow(NoSuchElementException::new);

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
