package com.ssafy.api.response;

import com.ssafy.entity.rdbms.Vote;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 청중 투표 결과 조회 API ([GET] /api/v1/vote/debates/{:debateId}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("VoteResponse")
public class VoteRes {
    @ApiModelProperty(name="토론 Id")
    Long debateId;

    @ApiModelProperty(name="mvp Id")
    Long mvpId;
    @ApiModelProperty(name="청중 투표 결과 리스트")
    List<VotePerspectiveRes> votePerspectiveResList;

    public static VoteRes of(List<Vote> voteList, Long debateId){
        VoteRes voteRes = new VoteRes();
        voteRes.setDebateId(debateId);
        Map<Long, Integer> mvpCountMap = new HashMap<>();
        Map<String, Integer> perspectiveCountMap = new HashMap<>();

        for(Vote vote : voteList){
            Long mvpId = vote.getMvpUser().getId();
            String perspectiveName = vote.getPerspective().getName();

            if(mvpCountMap.get(mvpId) == null)
                mvpCountMap.put(mvpId, 1);
            else
                mvpCountMap.put(mvpId, mvpCountMap.get(mvpId) + 1);

            if(perspectiveCountMap.get(perspectiveName) == null)
                perspectiveCountMap.put(perspectiveName, 1);
            else
                perspectiveCountMap.put(perspectiveName, perspectiveCountMap.get(perspectiveName)+1);
        }

        System.out.println(mvpCountMap);

        Long mvpId = -1l;
        int mvpCountMax = -1;
        for(Long id : mvpCountMap.keySet()){
            if(mvpCountMax >= mvpCountMap.get(id)) continue;

            mvpCountMax = mvpCountMap.get(id);
            mvpId = id;
        }
        voteRes.setMvpId(mvpId);

        int voteCount = voteList.size();
        List<VotePerspectiveRes> votePerspectiveResList = new ArrayList<>();
        for(String perspectiveName : perspectiveCountMap.keySet()){
            VotePerspectiveRes votePerspectiveRes = new VotePerspectiveRes();
            votePerspectiveRes.setPerspectiveName(perspectiveName);
            votePerspectiveRes.setPercent((float)perspectiveCountMap.get(perspectiveName)/voteCount);
            votePerspectiveResList.add(votePerspectiveRes);
        }
        voteRes.setVotePerspectiveResList(votePerspectiveResList);
        return voteRes;
    }
}
