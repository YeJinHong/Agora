package com.ssafy.api.service;

import com.ssafy.api.request.EvaluationBase;
import com.ssafy.api.request.EvaluationRegisterPostReq;
import com.ssafy.entity.rdbms.Evaluation;
import com.ssafy.repository.DebateRepository;
import com.ssafy.repository.EvaluationRepository;
import com.ssafy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 평가 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("evaluationService")
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final UserRepository userRepository;
    private final EvaluationRepository evaluationRepository;
    private final DebateRepository debateRepository;

    @Override
    public Evaluation createEvaluation(EvaluationRegisterPostReq evaluationRegisterPostReq) {
        Evaluation evaluation = new Evaluation();
        evaluation.setDebate(debateRepository.findById(evaluationRegisterPostReq.getDebateId()).orElseThrow(NoSuchElementException::new));
        evaluation.setEvaluator(userRepository.findById(evaluationRegisterPostReq.getEvaluatorId()).orElseThrow(NoSuchElementException::new));
        evaluation.setEvaluated(userRepository.findById(evaluationRegisterPostReq.getEvaluatedId()).orElseThrow(NoSuchElementException::new));
        evaluation.setContent(convertToText(evaluationRegisterPostReq.getContent()));
        return evaluationRepository.save(evaluation);
    }

    public String convertToText(ArrayList<EvaluationBase> content){
        StringBuilder sb = new StringBuilder("content : [\n");
        for(EvaluationBase evaluationBase : content){
            sb.append("\t{ ");
            sb.append(evaluationBase.getParentId()+", ");
            sb.append(evaluationBase.getId()+", ");
            sb.append(evaluationBase.getPoint());
            sb.append("}, ");
        }
        sb.append("\n]");
        return sb.toString();
    }
//    @Override
//    public void deleteDebate(Long debateId) {
//
//    }
}
