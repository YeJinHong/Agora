package com.ssafy.api.service;

import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.api.request.EvaluationRegisterPostReq;
import com.ssafy.entity.rdbms.Debate;
import com.ssafy.entity.rdbms.Evaluation;

public interface EvaluationService {
    Evaluation createEvaluation(EvaluationRegisterPostReq evaluationRegisterPostReq);
}
