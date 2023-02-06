package com.ssafy.api.response;

import com.ssafy.api.request.EvaluationBase;
import com.ssafy.entity.rdbms.Evaluation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * 토론 평가 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("EvaluationResponse")
public class EvaluationRes {
    @ApiModelProperty(name="피평가자 Id")
    String evaluatedId;
    @ApiModelProperty(name="전체 평가 내용 취합 결과", example="[1:[{2:5.0}, {3:4.5}], 4:[5:2.2,6:4.2], 7:[8:3.0,9:2.0]]")
    ArrayList<EvaluationBase> contentTotal;

    public static EvaluationRes of(List<Evaluation> evaluationList, String evaluatedId){
        EvaluationRes res = new EvaluationRes();
        res.setEvaluatedId(evaluatedId);
        // 평균 계산을 위한 항목(id)별 평가 수를 기억하기 위한 자료구조.
        // 현재는 모든 상호 평가가 같은 질문을 가지고 있기때문에 evaluationList.size()로 대체 가능하다. 현재는 확장가능성을 고려한것.
        Map<Long, Long> parentMap = new HashMap<>();
        Map<Long, Integer> countMap = new HashMap<>();
        Map<Long, Float> sumMap = new HashMap<>();

        for(Evaluation evaluation : evaluationList){
            // 평가 내용 content는 parentId, id, point 세개로 구성.
            // ex - content [ {1, 4, 5}, {1, 5, 4}, {2, 6, 5}, {3, 7, 5}, {3, 8, 2}, ]
            // => 1, 4, 5, 1, 5, 4, 2, 6, 5, 3, 7, 5, 3, 8, 2,
            String content = evaluation.getContent().substring(13, evaluation.getContent().length()-1).trim();
            content = content.replaceAll("\\{", "");
            content = content.replaceAll("}", "");
            content = content.replaceAll("\\s+", "");

            StringTokenizer st = new StringTokenizer(content, ",");
            while(st.hasMoreTokens()){
                Long parentId = Long.parseLong(st.nextToken().trim());
                Long id = Long.parseLong(st.nextToken());
                Float point = Float.parseFloat(st.nextToken());

                if(parentMap.get(id) == null)
                    parentMap.put(id, parentId);

                if(countMap.get(id) == null)
                    countMap.put(id, 1);
                else
                    countMap.put(id, countMap.get(id) + 1);

                if(sumMap.get(id) == null)
                    sumMap.put(id, point);
                else
                    sumMap.put(id, sumMap.get(id) + point);
            }

        }

        ArrayList<EvaluationBase> contentTotal = new ArrayList<>();

        List<Long> keyList = new ArrayList<>(sumMap.keySet());
        keyList.sort((s1, s2) -> s1.compareTo(s2));

        // 항목별 결과 반환
        for(Long id : keyList){
            Long parentId = parentMap.get(id);
            Integer count = countMap.get(id);
            Float sum = sumMap.get(id);

            EvaluationBase evaluationBase = new EvaluationBase();
            evaluationBase.setParentId(parentId);
            evaluationBase.setId(id);
            evaluationBase.setPoint(sum/count);
            contentTotal.add(evaluationBase);
        }
        res.setContentTotal(contentTotal);

        return res;
    }
}
